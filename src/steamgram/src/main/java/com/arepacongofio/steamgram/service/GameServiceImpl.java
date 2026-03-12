package com.arepacongofio.steamgram.service;


import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.repository.GameJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;

@Service
public class GameServiceImpl extends AbstractService<Game,Integer> {

    GameJpaRepository gameRepository;

    public GameServiceImpl(GameJpaRepository gameRepository){
        super(gameRepository);
    } 
}
