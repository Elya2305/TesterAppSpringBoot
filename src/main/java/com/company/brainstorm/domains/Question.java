package com.company.brainstorm.domains;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String context;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    private Set<Answer> answers;

    public Question() {
    }

    public Question(String context) {
        this.context = context;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Answer getCorrectAnswer() {
        return answers.stream()
                .filter(Answer::isCorrect)
                .findFirst()
                .get();
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", context='" + context + '\'' +
                '}';
    }
}
