package com.arepacongofio.steamgram.service.abst;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arepacongofio.steamgram.service.interfaces.generic.IGenericService;

public class AbstractService<T,ID> implements IGenericService<T,ID> {

    JpaRepository<T,ID> repo;
    public AbstractService(JpaRepository<T,ID> repo){
        this.repo=repo;
    }
    @Override
    public List<T> findAll() {
        return repo.findAll();
    }

    @Override
    public boolean existsById(ID id) {
        return repo.existsById(id);
    }

    @Override
    public T findById(ID id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public T save(T entity) {
        return repo.save(entity);
    }

    @Override
    public boolean deleteById(ID id) {
        if (!existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
    
}
