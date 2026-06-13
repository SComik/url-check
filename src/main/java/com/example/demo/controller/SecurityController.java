package com.example.demo.controller;

import com.example.demo.model.SecurityReport;
import com.example.demo.service.HttpCheckService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {

    private final HttpCheckService httpCheckService;

    public SecurityController(HttpCheckService httpCheckService) {
        this.httpCheckService = httpCheckService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/analyze")
    public String analyze(@RequestParam(required = false) String url, Model model) {

        SecurityReport report;
        try {
            report = httpCheckService.analyze(url);

            model.addAttribute("report", report);
            model.addAttribute("submittedUrl", url);
            return "index";
        } catch (Exception e) {
            
            return "index";
        }

    }

}
