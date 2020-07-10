package com.company.brainstorm.domains;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
    private Integer score;
    private boolean win;

    public Game(User user, Integer score, boolean win) {
        this.user = user;
        this.score = score;
        this.win = win;
    }

    public Game() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", user=" + user +
                ", score=" + score +
                ", win=" + win +
                '}';
    }
}
