// src/main/java/com/sportyshoes/controller/MainController.java
package com.sportyshoes.controller;

import com.sportyshoes.dao.*;
import com.sportyshoes.model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@Controller
public class MainController {

    private final UserDao userDao;
    private final AdminDao adminDao;
    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final OrderDao orderDao;

    public MainController(UserDao userDao,
                          AdminDao adminDao,
                          ProductDao productDao,
                          CategoryDao categoryDao,
                          OrderDao orderDao) {
        this.userDao = userDao;
        this.adminDao = adminDao;
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.orderDao = orderDao;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession sess,
                          Model model) {
        Admin admin = adminDao.findByUsernameAndPassword(email, password);
        if (admin != null) {
            sess.setAttribute("role", "admin");
            sess.setAttribute("admin", admin);
            return "redirect:/admin/dashboard";
        }
        User user = userDao.findByEmailAndPassword(email, password);
        if (user != null) {
            sess.setAttribute("role", "user");
            sess.setAttribute("user", user);
            sess.setAttribute("cart", new HashMap<Integer, Integer>());
            return "redirect:/products";
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(@RequestParam String fullName,
                           @RequestParam String email,
                           @RequestParam String password) {
        userDao.save(new User(0, email, fullName, password));
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession sess) {
        sess.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/products")
    public String products(HttpSession sess, Model model) {
        if (!"user".equals(sess.getAttribute("role"))) {
            return "redirect:/login";
        }
        model.addAttribute("products", productDao.findAll());
        return "products";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id, HttpSession sess) {
        if (!"user".equals(sess.getAttribute("role"))) {
            return "redirect:/login";
        }
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) sess.getAttribute("cart");
        cart.put(id, cart.getOrDefault(id, 0) + 1);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(HttpSession sess, Model model) {
        if (!"user".equals(sess.getAttribute("role"))) {
            return "redirect:/login";
        }
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) sess.getAttribute("cart");
        List<Map<String, Object>> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (var entry : cart.entrySet()) {
            Product p = productDao.findById(entry.getKey());
            int qty = entry.getValue();
            BigDecimal line = p.getPrice().multiply(BigDecimal.valueOf(qty));
            total = total.add(line);
            items.add(Map.of("product", p, "qty", qty, "line", line));
        }
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/checkout")
    public String doCheckout(HttpSession sess) {
        if (!"user".equals(sess.getAttribute("role"))) {
            return "redirect:/login";
        }
        User u = (User) sess.getAttribute("user");
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) sess.getAttribute("cart");
        BigDecimal total = cart.entrySet().stream()
            .map(e -> productDao.findById(e.getKey()).getPrice()
                       .multiply(BigDecimal.valueOf(e.getValue())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        int orderId = orderDao.saveOrder(u.getId(), total);
        cart.forEach((pid, qty) -> orderDao.saveItem(orderId, pid, qty));
        cart.clear();
        return "redirect:/products";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession sess) {
        if (!"admin".equals(sess.getAttribute("role"))) {
            return "redirect:/login";
        }
        return "admin/dashboard";
    }

    @GetMapping("/admin/customers")
    public String adminCustomers(HttpSession sess, Model model) {
        if (!"admin".equals(sess.getAttribute("role"))) {
            return "redirect:/login";
        }
        model.addAttribute("users", userDao.findAll());
        return "admin/customers";
    }

    @GetMapping("/admin/categories")
    public String adminCategories(HttpSession sess, Model model) {
        if (!"admin".equals(sess.getAttribute("role"))) {
            return "redirect:/login";
        }
        model.addAttribute("categories", categoryDao.findAll());
        return "admin/categories";
    }

    @PostMapping("/admin/categories")
    public String saveCategory(Category c) {
        categoryDao.save(c);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryDao.delete(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/products")
    public String adminProducts(HttpSession sess, Model model) {
        if (!"admin".equals(sess.getAttribute("role"))) {
            return "redirect:/login";
        }
        model.addAttribute("products", productDao.findAll());
        return "admin/products";
    }

    @PostMapping("/admin/products")
    public String saveProduct(Product p) {
        productDao.saveOrUpdate(p);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productDao.delete(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/reports")
    public String reports(HttpSession sess, Model model) {
        if (!"admin".equals(sess.getAttribute("role"))) {
            return "redirect:/login";
        }
        model.addAttribute("reports", orderDao.findReports());
        return "admin/reports";
    }
}
