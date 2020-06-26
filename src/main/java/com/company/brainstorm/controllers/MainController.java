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

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class MainController {
    UserService userService;
    List<Integer> totalGames;
    List<Double> avgScore;
    List<User> users;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
        this.users = userService.getAllUsers();
    }


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

    @GetMapping(value = {"/statistics", "/statistics/tg"})
    public String getStatistics(Model model){
        users.sort(Comparator.comparingInt(o -> userService.totalGame((User) o)).reversed());
        totalGames = getList(userService::totalGame);
        avgScore = getList(userService::avgScore);
        model.addAttribute("users", users)
                .addAttribute("totalGames",totalGames)
                .addAttribute("avgScore", avgScore);
        return "statistics";
    }

    @GetMapping("/statistics/avg")
    public String orderByAvgScore(Model model){
        users.sort(Comparator.comparingDouble(o -> userService.avgScore((User) o)).reversed());
        totalGames = getList(userService::totalGame);
        avgScore = getList(userService::avgScore);
        model.addAttribute("users", users)
                .addAttribute("totalGames",totalGames)
                .addAttribute("avgScore", avgScore);
        return "statistics";
    }

    private <T extends Number> List<T> getList(Function<User,T> func) {
        return users.stream()
                .map(func)
                .collect(Collectors.toList());
    }
}
