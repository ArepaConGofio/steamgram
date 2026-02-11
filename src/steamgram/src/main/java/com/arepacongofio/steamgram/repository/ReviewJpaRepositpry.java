package com.arepacongofio.steamgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.models.Review;

@Repository
public interface ReviewJpaRepositpry extends JpaRepository<Review,Integer>{
    
}
