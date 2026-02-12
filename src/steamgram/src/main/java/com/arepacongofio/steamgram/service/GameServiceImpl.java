package com.arepacongofio.steamgram.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.models.Game;
import com.arepacongofio.steamgram.repository.GameJpaRepository;
import com.arepacongofio.steamgram.service.interfaces.IService;

@Service
public class GameServiceImpl implements IService<Game,Integer> {

    GameJpaRepository repository;

    public GameServiceImpl(GameJpaRepository repository){
        this.repository = repository;
    } 

    @Override
    public List<Game> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Game findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Game save(Game entity) {
        return repository.save(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!existsById(id)) {
            return false;
        }
        Game deleteGame = findById(id);
        repository.delete(deleteGame);
        return true;
    }
    
}
