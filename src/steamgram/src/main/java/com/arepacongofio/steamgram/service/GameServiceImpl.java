package com.arepacongofio.steamgram.service;


import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.models.Game;
import com.arepacongofio.steamgram.repository.GameJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;

@Service
public class GameServiceImpl extends AbstractService<Game,Integer> {

    GameJpaRepository repository;

    public GameServiceImpl(GameJpaRepository repository){
        super(repository);
    } 
}
