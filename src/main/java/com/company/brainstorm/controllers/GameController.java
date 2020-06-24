package com.company.brainstorm.controllers;

import com.company.brainstorm.domains.Answer;
import com.company.brainstorm.domains.Question;
import com.company.brainstorm.domains.User;
import com.company.brainstorm.services.AnswerService;
import com.company.brainstorm.services.GameService;
import com.company.brainstorm.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {
    QuestionService questionService;
    AnswerService answerService;
    GameService gameService;
    List<Question> questions;
    List<Answer> userAnswers;
    int counter;

    @Autowired
    public GameController(QuestionService questionService, GameService gameService,
                          AnswerService answerService) {
        this.questionService = questionService;
        this.gameService = gameService;
        this.answerService = answerService;
        this.questions = questionService.random5();
        this.counter = 0;
        this.userAnswers = new ArrayList<>();
    }

    @GetMapping("/game")
    public String game(@AuthenticationPrincipal User user, Model model){
        if(counter == 0) userAnswers.clear();
        if(counter > 0) userAnswers.add(new Answer("", false));
        if(counter==5) return endGame(model, user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("question", questions.get(counter++));
        return "game";
    }

    @PostMapping("/game")
    public String checkQuestion(@RequestParam String id, @AuthenticationPrincipal User user, Model model){
        model.addAttribute("username", user.getUsername());
        if(counter == 1) userAnswers.clear();
        Answer answer = answerService.getAnswerById(Integer.parseInt(id));
        userAnswers.add(answer);
        gameService.checkAnswer(answer, counter, user);
        if(counter==5) return endGame(model, user);
        model.addAttribute("question", questions.get(counter++));
        return "game";
    }

    private String endGame(Model model, User user){
        counter = 0;
        model.addAttribute("questions",questions);
        model.addAttribute("answers",userAnswers);
        model.addAttribute("username", user.getUsername());
        questions = questionService.random5();
        gameService.getVerdicts(model);
        return "results";
    }
}
