package com.arepacongofio.steamgram.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.arepacongofio.steamgram.domain.requests.UserEditRequest;
import com.arepacongofio.steamgram.domain.requests.UserFindRequest;
import com.arepacongofio.steamgram.domain.requests.UserRequest;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.service.interfaces.generic.IGenericService;

/**
 * Users service. Manage basic operation with user entities.
 * 
 * @author strSalazar.
 */
public interface IUserService extends IGenericService<User, Integer> {

    /**
     * Creates a new user with the given request
     * 
     * @param request Request with the user data
     * @return UserResponse
     */
    public UserResponse createUser(UserRequest request);

    /**
     * Edits an existing user with the given request
     * 
     * @param request Request with the user data
     * @return UserResponse
     */
    public UserResponse editUser(UserEditRequest request);

    /**
     * Find users by nickname and name
     * 
     * @param nickname Nickname to search for
     * @return List<UserResponse>
     */
    public List<UserResponse> findUserByName(Pageable pageable, UserFindRequest request);

    /**
     * Check if user exists by nickname
     * 
     * @param nickname Nickname to check
     * @return Boolean
     */
    public Boolean existsByNickname(String nickname);

    /**
     * Get user's games
     * 
     * @param id User to get games from
     * @return List<Game>
     */
    public List<Game> getUserGames(Integer id);

    /**
     * Get user's posts
     * 
     * @param id user to get posts from
     * @return List<Post>
     */
    public List<Post> getUserPosts(Integer id);

    /**
     * Get user's reviews
     * 
     * @param id user to get reviews from
     * @return List<Review>
     */
    public List<Review> getUserReviews(Integer id);

    /**
     * Get user by nickname
     * 
     * @param nickname user's nickname
     * @return User
     */
    public User getUserByNickname(String nickname);
}
