package com.sportyshoes.controller;

import com.sportyshoes.model.Product;
import com.sportyshoes.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {
    private final ProductService prodSvc;
    private final CategoryService catSvc;
    public ProductAdminController(ProductService p, CategoryService c) {
        this.prodSvc = p; this.catSvc = c;
    }

    @GetMapping
    public String list(Model m) {
        m.addAttribute("products", prodSvc.listAll());
        m.addAttribute("categories", catSvc.listAll());
        return "admin/products";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product p) {
        prodSvc.save(p);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        prodSvc.delete(id);
        return "redirect:/admin/products";
    }
}
