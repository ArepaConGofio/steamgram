package com.arepacongofio.steamgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.models.Game;

@Repository
public interface GameJpaRepository extends JpaRepository<Game,Integer>{
    
}
