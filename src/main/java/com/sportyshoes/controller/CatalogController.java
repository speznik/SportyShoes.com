package com.sportyshoes.controller;

import com.sportyshoes.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogController {
    private final ProductService svc;
    public CatalogController(ProductService svc) { this.svc = svc; }

    @GetMapping({"/", "/catalog"})
    public String viewCatalog(Model m) {
        m.addAttribute("products", svc.listAll());
        return "catalog";
    }
}
