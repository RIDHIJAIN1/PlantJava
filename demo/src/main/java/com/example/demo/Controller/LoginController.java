package com.example.demo.Controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;



@Controller

public class LoginController {

    @GetMapping("/login")

    public String showLogin() {

        return "login";

    }



    @GetMapping("/tutorial/new")

    public String welcome(Model model) {

        return "tutorial_form";

    }

}