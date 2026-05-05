package com.arepacongofio.steamgram.service.interfaces;

import java.util.List;

import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.service.interfaces.generic.IGenericService;

public interface IGameService extends IGenericService<Game,Integer>{
    public List<Game> findIgdbGamesByTitle(String title);
    public Game getGameByIgdbId(String igdbId);
    public List<Post> getGamePosts(Game game);
    public List<Review> getGameReviews(Game game);
    public Game saveGameIntoProfile(Game game, User user);
}
