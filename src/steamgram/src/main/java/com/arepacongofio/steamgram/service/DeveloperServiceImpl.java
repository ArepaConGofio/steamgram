package com.arepacongofio.steamgram.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.models.Developer;
import com.arepacongofio.steamgram.repository.DeveloperJpaRepository;
import com.arepacongofio.steamgram.service.interfaces.IService;

@Service
public class DeveloperServiceImpl implements IService<Developer, Integer> {

    DeveloperJpaRepository repository;

    public DeveloperServiceImpl(DeveloperJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Developer> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Developer findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Developer save(Developer entity) {
        return repository.save(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!existsById(id)) {
            return false;
        }
        Developer deleteDeveloper = findById(id);
        repository.delete(deleteDeveloper);
        return true;
    }

}
