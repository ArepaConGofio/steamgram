package com.arepacongofio.steamgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.entities.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Integer>{
    
    /**
     * Comprueba si existe un usuario por su email
     * @param email del usuario
     * @return true/false
     */
    boolean existsByEmail(String email);
    
    /**
     * comprueba si existe un usuario por su email
     * @param nickname del usuario
     * @return true/false
     */
    boolean existsByNickname(String nickname);
}
