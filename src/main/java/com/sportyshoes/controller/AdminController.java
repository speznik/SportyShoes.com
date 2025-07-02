package com.sportyshoes.controller;

import com.sportyshoes.model.Admin;
import com.sportyshoes.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService svc;
    public AdminController(AdminService svc) { this.svc = svc; }

    @GetMapping("/login")
    public String loginForm() { return "admin/login"; }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model m) {
        Admin a = svc.authenticate(username, password);
        if (a==null) {
            m.addAttribute("error","Invalid credentials");
            return "admin/login";
        }
        session.setAttribute("admin", a);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() { return "admin/dashboard"; }
}
