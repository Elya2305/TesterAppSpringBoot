package com.company.brainstorm.services;

import com.company.brainstorm.domains.Question;
import com.company.brainstorm.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {
    QuestionRepository questionRepository;
    List<Question> questions;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
//        questions = questionRepository.findAll();
        System.out.println(questions);
    }

    public List<Question> random5() {
        if (questions == null) {
            questions = questionRepository.findAll();
        }
        Collections.shuffle(questions);
        return questions;
    }

    //if there were more than 5 questions in db:

//    public List<Question> random5() {
//        List<Integer> list = IntStream
//                .rangeClosed(1, questions.size())
//                .boxed()
//                .collect(Collectors.toList());
//        Collections.shuffle(list);
//        return questionRepository.findByIdIn(list.subList(0, 5));
//    }

}
