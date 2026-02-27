package com.arepacongofio.steamgram.service;

import com.arepacongofio.steamgram.models.User;
import com.arepacongofio.steamgram.repository.UserJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;

public class UserServiceImpl extends AbstractService<User, Integer >{
    
    UserJpaRepository repository;

    public UserServiceImpl(UserJpaRepository repository) {
        super(repository);
    }
    
}
