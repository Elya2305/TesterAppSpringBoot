package com.company.brainstorm.domains;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue
    private Integer id;
    private String context;
    private boolean correct;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_question")
    private Question question;


    public Answer(String context, boolean correct) {
        this.context = context;
        this.correct = correct;
    }

    public Answer(String context, boolean correct, Question question) {
        this.context = context;
        this.correct = correct;
        this.question = question;
    }

    public Answer() {
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

    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isCorrect() {
        return correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", correct=" + correct +
                ", question=" + question +
                '}';
    }
}
