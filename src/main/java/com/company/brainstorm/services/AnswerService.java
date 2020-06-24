package com.company.brainstorm.services;

import com.company.brainstorm.domains.Answer;
import com.company.brainstorm.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public Answer getAnswerById(int id){
        return answerRepository.findById(id);
    }


}
