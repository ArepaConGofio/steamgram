package com.arepacongofio.steamgram.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Review;

@Repository
public interface ReviewJpaRepository extends JpaRepository<Review, Integer> {
    List<Review> findByGame(Pageable pageable, Game game);
}
