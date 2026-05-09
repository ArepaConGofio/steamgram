package com.arepacongofio.steamgram.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arepacongofio.steamgram.entities.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {

    /**
     * Busca a un usuario por su nickname
     * @param nickname del usuario
     * @return User
     */
    Optional<User> findByNickname(String nickname);
    
    /**
     * Busca usuarios por nombre y nickname
     * @param pageable
     * @param name
     * @param nickname
     * @return List<User>
     */
    List<User> findByNameAndNickname(Pageable pageable,String name, String nickname);

    /**
     * Comprueba si existe un usuario por su email
     * @param email del usuario
     * @return true/false
     */
    boolean existsByEmail(String email);
    
    /**
     * comprueba si existe un usuario por su nickname
     * @param nickname del usuario
     * @return true/false
     */
    boolean existsByNickname(String nickname);
}
