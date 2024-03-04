package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/tutorials/new")
    public String addTutorial(Model model) {
        model.addAttribute("name", "Ajay");
        return "tutorial_form";
    }


}