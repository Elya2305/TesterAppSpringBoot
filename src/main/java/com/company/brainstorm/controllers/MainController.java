package com.company.brainstorm.controllers;

import com.company.brainstorm.domains.User;
import com.company.brainstorm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping(value = {"/profile", "/"})
    public String profile(@AuthenticationPrincipal User user, Model model){
        addCommonAttributes(user, model);
        return "profile";
    }

    private void addCommonAttributes(User user, Model model) {
        model.addAttribute("username", user.getUsername())
                .addAttribute("email", user.getEmail())
                .addAttribute("totalGame", userService.totalGame(user))
                .addAttribute("totalWin", userService.totalWin(user))
                .addAttribute("maxScore", userService.maxScore(user))
                .addAttribute("avgScore", userService.avgScore(user));
    }

    @PostMapping("/profile/change")
    public String change(@RequestParam String username, @AuthenticationPrincipal User user, Model model){
        if(username.isBlank() || username.equals(user.getUsername())){
            model.addAttribute("error", "New username can't be blank or the same!");
        }else{
            user.setUsername(username);
            userService.save(user);
            model.addAttribute("success", "Username was successfully changed");
        }
        addCommonAttributes(user,model);
        return "profile";
    }
}
