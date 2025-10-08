package org.example.demo_decentralize_application_permissions.practice.p1.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practice/p1")
public class SecurityController {
    private String getPrincipal() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @GetMapping(value = {"/", "home"})
    public String Homepage(Model model) {
        model.addAttribute("user", getPrincipal());
        return "practice/p1/welcome";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("user", getPrincipal());
        return "practice/p1/admin";
    }

    @GetMapping("/dba")
    public String dbaPage(Model model) {
        model.addAttribute("user", getPrincipal());
        return "practice/p1/dba";
    }

    @GetMapping("/accessDenied")
    public String accessDeniedPage(Model model) {
        model.addAttribute("user", getPrincipal());
        return "practice/p1/access_denied";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }
}