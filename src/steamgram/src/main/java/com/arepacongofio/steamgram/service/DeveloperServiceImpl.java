package com.arepacongofio.steamgram.service;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.models.Developer;
import com.arepacongofio.steamgram.models.Game;
import com.arepacongofio.steamgram.repository.DeveloperJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.IDeveloperService;

@Service
public class DeveloperServiceImpl extends AbstractService<Developer,Integer> implements IDeveloperService {

    DeveloperJpaRepository repository;

    public DeveloperServiceImpl(DeveloperJpaRepository repository) {
        super(repository);
    }

    @Override
    public Game publishGame(Game game) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'publishGame'");
    }

}
