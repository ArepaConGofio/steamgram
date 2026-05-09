package com.arepacongofio.steamgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.entities.Like;

import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.User;
import java.util.Optional;

@Repository
public interface LikeJpaRepository extends JpaRepository<Like,Integer>{
    Optional<Like> findByUserAndPost(User user, Post post);
}
