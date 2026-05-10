package com.arepacongofio.steamgram.service.abst;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.arepacongofio.steamgram.service.interfaces.generic.IGenericService;

/**
 * Abstract service class. Basic operations for entities
 * 
 * @author strSalazar
 */
public abstract class AbstractService<T, K> implements IGenericService<T, K> {

    JpaRepository<T, K> repo;

    protected AbstractService(JpaRepository<T, K> repo) {
        this.repo = repo;
    }

    @Override
    public List<T> findAll(Pageable pageable) {
        return repo.findAll(pageable).getContent();
    }

    @Override
    public boolean existsById(K id) {
        return repo.existsById(id);
    }

    @Override
    public T findById(K id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public T save(T entity) {
        return repo.save(entity);
    }

    @Override
    public boolean deleteById(K id) {
        if (!existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    public JpaRepository<T, K> getRepo() {
        return repo;
    }

}
