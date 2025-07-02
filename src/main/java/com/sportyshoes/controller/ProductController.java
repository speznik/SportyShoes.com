package com.sportyshoes.controller;

import com.sportyshoes.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private final ProductService svc;
    public ProductController(ProductService svc) { this.svc = svc; }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable int id, Model m) {
        m.addAttribute("product", svc.get(id));
        return "product-detail";
    }
}
