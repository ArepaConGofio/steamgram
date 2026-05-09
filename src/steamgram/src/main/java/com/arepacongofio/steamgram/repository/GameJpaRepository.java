package com.arepacongofio.steamgram.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface GameJpaRepository extends JpaRepository<Game,Integer>{
    
    public List<Game> findByTitleIgnoreCaseContaining(Pageable pageable, String title);

    public java.util.Optional<Game> findByIdIgdb(Integer idIgdb);

    @Query("SELECT p FROM Post p WHERE p.game = :game")
    public List<Post> getGamePosts(Pageable pageable, @Param("game") Game game);

    @Query("SELECT r FROM Review r WHERE r.game = :game")
    public List<Review> getGameReviews(Pageable pageable, @Param("game") Game game);

}
