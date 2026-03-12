package com.arepacongofio.steamgram.service;

import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.repository.UserJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;

public class UserServiceImpl extends AbstractService<User, Integer >{
    
    UserJpaRepository userRepository;

    public UserServiceImpl(UserJpaRepository userRepository) {
        super(userRepository);
    }
    
}
