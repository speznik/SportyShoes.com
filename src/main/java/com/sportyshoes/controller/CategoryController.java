package com.sportyshoes.controller;

import com.sportyshoes.model.Category;
import com.sportyshoes.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    private final CategoryService svc;
    public CategoryController(CategoryService svc) { this.svc = svc; }

    @GetMapping
    public String list(Model m) {
        m.addAttribute("categories", svc.listAll());
        return "admin/categories";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Category c) {
        svc.save(c);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        svc.delete(id);
        return "redirect:/admin/categories";
    }
}
