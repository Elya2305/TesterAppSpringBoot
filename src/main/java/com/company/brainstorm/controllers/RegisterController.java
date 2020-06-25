package com.company.brainstorm.controllers;

import com.company.brainstorm.domains.User;
import com.company.brainstorm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        if(!user.getPassword().equals(user.getConfirmPassword())){
            model.addAttribute("message", "Check your password input");
            return "registration";
        }
        boolean addUser = userService.addUser(user);
        System.out.println("User after registr: " + user);
        if(addUser){
            return "redirect:/login";
        }
        model.addAttribute("message", "User already exists!");
        return "login";
    }

    @GetMapping("/registration")
    public String register(){
//        System.out.println(user);
        return "registration";
    }

}
