package com.arepacongofio.steamgram.service.interfaces;

import java.util.List;

public interface IService<T,ID> {
    
    /**
     * Devuelve todas las entities almacenadas.
     * @return lista con todas las entities
     */
    List<T> findAll();

    /**
     * Metodo que indica si existe una entity con el id dado.
     * @param id identificador unico de la entity
     * @return true si existe una entity con ese id, false en caso contrario
     */
    boolean existsById(ID id);

    /**
     * Metodo que busca una entity por su id.
     * @param id identificador unico de la entity
     * @return la entity encontrada, o null si no existe
     */
    T findById(ID id);

    /**
     * Inserta o actualiza una entity.
     * - Si la entity no tiene id, la implementacion debe generarlo.
     * - Si la entity ya existe, se actualiza.
     * @param entity a guardar
     * @return la entity guardada, incluida la informacion final 
     * (por ejemplo el id asignado)
     */
    T save(T entity);

    /**
     * Elimina la entity con el id indicado.
     * @param id identificador unico de la entity
     * @return true si se borro la entity, false si no existia
     */
    boolean deleteById(ID id);
}
