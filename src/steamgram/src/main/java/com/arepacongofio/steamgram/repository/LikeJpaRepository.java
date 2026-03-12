package com.arepacongofio.steamgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.entities.Like;

@Repository
public interface LikeJpaRepository extends JpaRepository<Like,Integer>{
    
}
