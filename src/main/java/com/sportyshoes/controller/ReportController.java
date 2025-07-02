package com.sportyshoes.controller;

import com.sportyshoes.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/reports")
public class ReportController {
    private final OrderService svc;
    public ReportController(OrderService svc) { this.svc = svc; }

    @GetMapping
    public String viewReports(@RequestParam(required=false) String from,
                              @RequestParam(required=false) String to,
                              Model m) {
        // implement filter logic in service as needed
        m.addAttribute("reports", svc.getItems(0));
        return "admin/reports";
    }
}
