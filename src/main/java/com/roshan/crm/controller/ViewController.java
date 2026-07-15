package com.roshan.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/leads")
    public String leads() {
        return "leads";
    }

    @GetMapping("/deals")
    public String deals() {
        return "deals";
    }

    @GetMapping("/tasks")
    public String tasks() {
        return "tasks";
    }

    @GetMapping("/follow-ups")
    public String followUps() {
        return "follow-ups";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }
}
