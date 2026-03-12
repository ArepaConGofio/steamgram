package com.arepacongofio.steamgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.entities.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Integer>{
    
}
