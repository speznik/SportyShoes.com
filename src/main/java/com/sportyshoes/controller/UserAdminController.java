package com.sportyshoes.controller;

import com.sportyshoes.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {
    private final UserService svc;
    public UserAdminController(UserService svc) { this.svc = svc; }

    @GetMapping
    public String list(Model m, @RequestParam(required=false) String q) {
        m.addAttribute("users", 
            q==null ? svc.listAll() : svc.search(q));
        return "admin/users";
    }
}
