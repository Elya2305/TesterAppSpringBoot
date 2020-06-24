package com.company.brainstorm.repositories;

import com.company.brainstorm.domains.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByIdIn(Collection<Integer> id);
}