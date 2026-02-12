package com.arepacongofio.steamgram.service;

import java.util.List;

import com.arepacongofio.steamgram.models.User;
import com.arepacongofio.steamgram.repository.UserJpaRepository;
import com.arepacongofio.steamgram.service.interfaces.IService;

public class UserServiceImpl implements IService<User, Integer >{
    
    UserJpaRepository repository;

    public UserServiceImpl(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!existsById(id)) {
            return false;
        }
        User deleteUser =  findById(id);
        repository.delete(deleteUser);
        return true;
    }

    
}
