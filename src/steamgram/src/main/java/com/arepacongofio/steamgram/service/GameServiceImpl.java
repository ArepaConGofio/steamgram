package com.arepacongofio.steamgram.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.domain.requests.SaveGameRequest;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.repository.GameJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.IGameService;

@Service
public class GameServiceImpl extends AbstractService<Game,Integer> implements IGameService {

    GameJpaRepository gameRepository;

    public GameServiceImpl(GameJpaRepository gameRepository){
        super(gameRepository);
    }

    @Override
    public List<Post> getGamePosts(Game game) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGamePosts'");
    }

    @Override
    public List<Review> getGameReviews(Game game) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameReviews'");
    }

    @Override
    public List<Game> findIgdbGamesByTitle(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findIgdbGamesByTitle'");
    }

    @Override
    public Game saveGameIntoProfile(SaveGameRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveGameIntoProfile'");
    }

    @Override
    public Game getGameByIgdbId(String igdbId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameByIgdbId'");
    } 
}
