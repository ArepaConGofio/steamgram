package com.arepacongofio.steamgram.service.interfaces;

import java.util.List;

import com.arepacongofio.steamgram.domain.requests.UserEditRequest;
import com.arepacongofio.steamgram.domain.requests.UserRequest;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.service.interfaces.generic.IGenericService;

public interface IUserService extends IGenericService<User, Integer> {

    public UserResponse createUser(UserRequest request);
    
    public UserResponse editUser(UserEditRequest request);
    public List<User> findUserByName(String nickname, String name);
    public Boolean checkExistsByName(String nickname);
    public List<Game> getUserGames(User user);
    public List<Post> getUserPosts(User user);
    public List<Review> getUserReviews(User user);
    public List<User> getUserFollows(User user);
    public List<User> getUserFollowers(User user);

}
