package com.arepacongofio.steamgram.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.domain.requests.SaveGameRequest;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
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
    public List<Game> findIgdbGamesByTitle(Pageable pageable, String title) {
        return gameRepository.findByTitleIgnoreCaseContaining(pageable, title);
    }

    @Override
    public Game getGameByIgdbId(String igdbId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameByIgdbId'");
    }

    @Override
    public List<Post> getGamePosts(Pageable pageable, Integer id) {
        Game game = gameRepository.findById(id).orElse(null);
        if(game != null){
            return gameRepository.getGamePosts(pageable, game);
        }
        return List.of();
    }

    @Override
    public List<Review> getGameReviews(Pageable pageable, Integer id) {
        Game game = gameRepository.findById(id).orElse(null);
        if(game != null){
            return gameRepository.getGameReviews(pageable, game);
        }
        return List.of();
    }

    @Override
    public Game saveGameIntoProfile(SaveGameRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveGameIntoProfile'");
    }

}
