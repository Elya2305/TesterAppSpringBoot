package com.company.brainstorm;

import com.company.brainstorm.domains.Answer;
import com.company.brainstorm.domains.Question;
import com.company.brainstorm.domains.Role;
import com.company.brainstorm.repositories.AnswerRepository;
import com.company.brainstorm.repositories.QuestionRepository;
import com.company.brainstorm.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ApplicationSpringBoot implements CommandLineRunner {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    RoleRepository roleRepository;


    public static void main(String[] args) {
        SpringApplication.run(ApplicationSpringBoot.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Question question1 = new Question("How many rings does the Olympic flag have?");
        Question question2 = new Question("How many carats are in pure gold?");
        Question question3 = new Question("The capital of Madagascar?");
        Question question4 = new Question("What was da Vinci's first name?");
        Question question5 = new Question("Which of these nations is NOT in South America?");

        Set<Answer> answers1 = new HashSet<>(List.of(
                new Answer("6", false, question1),
                new Answer("5", true, question1),
                new Answer("7", false, question1)
                ));
        question1.setAnswers(answers1);

        Set<Answer> answers2 = new HashSet<>(List.of(
                new Answer("10", false, question2),
                new Answer("18", false, question2),
                new Answer("24", true, question2)
        ));
        question2.setAnswers(answers2);

        Set<Answer> answers3 = new HashSet<>(List.of(
                new Answer("Antananarivo", true, question3),
                new Answer("Nicaragua", false, question3),
                new Answer("Caracas", false, question3)
        ));
        question3.setAnswers(answers3);

        Set<Answer> answers4 = new HashSet<>(List.of(
                new Answer("Leonardo", true, question4),
                new Answer("Vinci", false, question4),
                new Answer("Mona", false, question4)
        ));
        question4.setAnswers(answers4);

        Set<Answer> answers5 = new HashSet<>(List.of(
                new Answer("Argentina", false, question5),
                new Answer("Bulgaria", true, question5),
                new Answer("Brazil", false, question5)
        ));
        question5.setAnswers(answers5);

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);
        questionRepository.save(question4);
        questionRepository.save(question5);

        answerRepository.saveAll(answers1);
        answerRepository.saveAll(answers2);
        answerRepository.saveAll(answers3);
        answerRepository.saveAll(answers4);
        answerRepository.saveAll(answers5);

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
    }
}
