package com.arepacongofio.steamgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.entities.Comment;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment,Integer> {
    
}
