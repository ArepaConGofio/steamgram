package com.arepacongofio.steamgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.models.Post;

@Repository
public interface PostJpaRepository extends JpaRepository<Post,Integer> {
    
}
