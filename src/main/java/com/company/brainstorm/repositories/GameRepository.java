package com.company.brainstorm.repositories;

import com.company.brainstorm.domains.Game;
import com.company.brainstorm.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByUser(User user);

    List<Game> findByUserAndWin(User user, boolean win);

    @Query("select max(g.score) from Game g where g.user=:user")
    int findMaxScore(@Param("user") User user);

    @Query("select avg(g.score) from Game g where g.user=:user")
    double findAvrScore(@Param("user") User user);

}
