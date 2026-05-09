package com.arepacongofio.steamgram.service.interfaces.generic;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface IGenericService<T, K> {

    /**
     * Devuelve todas las entities almacenadas.
     * 
     * @return lista con todas las entities
     */
    List<T> findAll(Pageable pageable);

    /**
     * Metodo que indica si existe una entity con el id dado.
     * 
     * @param id identificador unico de la entity
     * @return true si existe una entity con ese id, false en caso contrario
     */
    boolean existsById(K id);

    /**
     * Metodo que busca una entity por su id.
     * 
     * @param id identificador unico de la entity
     * @return la entity encontrada, o null si no existe
     */
    T findById(K id);

    /**
     * Inserta o actualiza una entity.
     * - Si la entity no tiene id, la implementacion debe generarlo.
     * - Si la entity ya existe, se actualiza.
     * 
     * @param entity a guardar
     * @return la entity guardada, incluida la informacion final
     *         (por ejemplo el id asignado)
     */
    T save(T entity);

    /**
     * Elimina la entity con el id indicado.
     * 
     * @param id identificador unico de la entity
     * @return true si se borro la entity, false si no existia
     */
    boolean deleteById(K id);
}
