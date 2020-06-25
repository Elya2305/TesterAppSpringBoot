package com.company.brainstorm.services;

import com.company.brainstorm.domains.Answer;
import com.company.brainstorm.domains.Game;
import com.company.brainstorm.domains.User;
import com.company.brainstorm.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GameService {
    private int score;
    private GameRepository gameRepository;
    private Game game;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.score = 0;
    }

    public void checkAnswer(Answer answer, int counter, User user){
        if(answer.isCorrect()) score++;
        if(counter == 5){
            game = new Game(user, score, score >= 3);
            gameRepository.save(game);
            score = 0;
        }
    }

    public void getVerdicts(Model model, boolean stopGame, User user) {
        if(stopGame){
            game = new Game(user, score, score >= 3);
        }
        if(game.getScore() >= 3){
            model.addAttribute("verdict","Congratulations! You've won. Your score is " + game.getScore());
            model.addAttribute("win", true);
        }else {
            model.addAttribute("verdict", "You've lost :(. Your score is " + game.getScore() + " but you need at least 3");
            model.addAttribute("win", false);
        }
    }
}
