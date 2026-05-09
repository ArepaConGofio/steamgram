package com.arepacongofio.steamgram.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.arepacongofio.steamgram.domain.requests.SaveGameRequest;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.service.interfaces.generic.IGenericService;

public interface IGameService extends IGenericService<Game, Integer> {

    /**
     * Find games in IGDB by title
     * 
     * @param Pageable - Page request
     * @param title    of the game
     * @return List<Game>
     */
    public List<Game> findIgdbGamesByTitle(Pageable pageable, String title);

    /**
     * Get game by IGDB id
     * 
     * @param igdbId of the game
     * @return Game
     */
    public Game getGameByIgdbId(String igdbId);

    /**
     * Get game posts
     * 
     * @param Pageable - Page request
     * @param id     to get Posts
     * @return List<Post>
     */
    public List<Post> getGamePosts(Pageable pageable, Integer id);

    /**
     * Get game reviews
     * 
     * @param pageable
     * @param id     to get Reviews
     * @return List<Review>
     */
    public List<Review> getGameReviews(Pageable pageable, Integer id);

    /**
     * Save game into profile
     * 
     * @param request to save game
     * @return Game
     */
    public Game saveGameIntoProfile(SaveGameRequest request);
}
