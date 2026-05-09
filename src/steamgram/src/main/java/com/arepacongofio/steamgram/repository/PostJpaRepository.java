package com.arepacongofio.steamgram.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;

@Repository
public interface PostJpaRepository extends JpaRepository<Post,Integer> {
    List<Post> findByGame(Pageable pageable, Game game);
}
