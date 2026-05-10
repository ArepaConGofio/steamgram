package com.arepacongofio.steamgram.service.interfaces.generic;

import java.util.List;

import org.springframework.data.domain.Pageable;

/**
 * Generic service interface. Basic operations for entities.
 * 
 * @author strSalazar
 */
public interface IGenericService<T, K> {

    /**
     * Find all the entities
     * 
     * @return List<T>
     */
    List<T> findAll(Pageable pageable);

    /**
     * Method that indicates if an entity with the given id exists
     * 
     * @param id id from the entity
     * @return true/false
     */
    boolean existsById(K id);

    /**
     * Method that find an entity by its id
     * 
     * @param id id from the entity
     * @return the entity found, or null if it doesn't exist
     */
    T findById(K id);

    /**
     * Method that inserts or updates an entity
     * 
     * @param entity to save
     * @return the entity saved
     */
    T save(T entity);

    /**
     * Method that deletes an entity by its id
     * 
     * @param id id from the entity
     * @return true/false
     */
    boolean deleteById(K id);
}
