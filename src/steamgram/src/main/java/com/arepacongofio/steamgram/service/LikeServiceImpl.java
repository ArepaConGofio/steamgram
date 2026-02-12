package com.arepacongofio.steamgram.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.models.Like;
import com.arepacongofio.steamgram.repository.LikeJpaRepository;
import com.arepacongofio.steamgram.service.interfaces.IService;

@Service
public class LikeServiceImpl implements IService<Like,Integer>{

    LikeJpaRepository repository;

    public LikeServiceImpl(LikeJpaRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Like> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return existsById(id);
    }

    @Override
    public Like findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Like save(Like entity) {
        return repository.save(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!existsById(id)) {
            return false;
        }
        Like deleteLike = findById(id);
        repository.delete(deleteLike);
        return true;
    }
    
}
