package com.company.brainstorm.repositories;

import com.company.brainstorm.domains.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Answer findById(int id);
}