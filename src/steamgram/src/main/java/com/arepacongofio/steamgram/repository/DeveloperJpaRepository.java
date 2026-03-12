package com.arepacongofio.steamgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.entities.Developer;

@Repository
public interface DeveloperJpaRepository extends JpaRepository<Developer,Integer>{
    
}
