package com.example.demo.Controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    private final UserRepository userRepository;

    @Autowired
    public SignupController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/signup")
    public String showSignUp(Model model) {
        model.addAttribute("user",new User());
//        model.getAttribute("product");
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignUp(User user) {
        // Add your signup logic here
        userRepository.save(user);
        return "redirect:/login";
    }
}
