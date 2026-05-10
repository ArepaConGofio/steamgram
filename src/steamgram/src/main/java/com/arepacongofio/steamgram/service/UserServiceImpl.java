package com.arepacongofio.steamgram.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arepacongofio.steamgram.domain.requests.UserEditRequest;
import com.arepacongofio.steamgram.domain.requests.UserFindRequest;
import com.arepacongofio.steamgram.domain.requests.UserRequest;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.mappers.UserMapper;
import com.arepacongofio.steamgram.repository.UserJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl extends AbstractService<User, Integer> implements IUserService {

    private UserJpaRepository userRepository;
    private UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(JpaRepository<User, Integer> userJpaRepository, PasswordEncoder passwordEncoder,
            UserJpaRepository userRepository) {
        super(userJpaRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Insert initial data to app demo.
     */
    @PostConstruct
    public void loadSeedData() {
        createUser(new UserRequest(null, "admin", "admin@mail.com", "admin"));
        createUser(new UserRequest(null, "jesus", "jesus@mail.com", "jesus"));
        createUser(new UserRequest(null, "salas", "salas@mail.com", "salas"));
        createUser(new UserRequest(null, "user", "user@mail.com", "user"));
        createUser(new UserRequest("iLoveSteam", "gabe", "gabe@mail.com", "gabe"));
    }

    @Autowired
    public void setUserRepository(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())
                || userRepository.existsByNickname(request.getNickname())) {
            return null;
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);
        return userMapper.toResponse(userRepository.save(userMapper.toEntity(request)));
    }

    public List<Game> getUserGames(Integer id) {
        if (id == null || !existsById(id)) {
            return List.of();
        }
        User user = findById(id);
        if (user.getGames() == null) {
            return List.of();
        }
        List<Game> games = new ArrayList<>(user.getGames());
        return reverseList(games);
    }

    public User getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname).orElse(null);
    }

    public List<Post> getUserPosts(Integer id) {
        if (id == null || !existsById(id)) {
            return List.of();
        }
        User user = findById(id);
        List<Post> posts = new ArrayList<>(user.getPosts());
        return reverseList(posts);
    }

    public List<Review> getUserReviews(Integer id) {
        if (id == null || !existsById(id)) {
            return List.of();
        }
        User user = findById(id);
        List<Review> reviews = new ArrayList<>(user.getReviews());
        return reverseList(reviews);
    }

    @Transactional
    public UserResponse editUser(UserEditRequest request) {
        User user = userRepository.findById(request.getId()).orElse(null);
        if (user == null) {
            return null;
        }
        user.setName(request.getName());
        user.setAvatarUrl(request.getAvatarUrl());
        return userMapper.toResponse(userRepository.save(user));
    }

    public List<UserResponse> findUserByName(Pageable pageable, UserFindRequest request) {
        if (request == null) {
            return List.of();
        }
        return userMapper.toResponseList(
                userRepository.findByNameAndNickname(pageable, request.getName(), request.getNickname()));
    }

    public Boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    /**
     * Reverse a list.
     * @param list
     * @param <T>
     * @return List reversed
     */
    private <T> List<T> reverseList(List<T> list) {
        List<T> newList = new ArrayList<>(list);
        Collections.reverse(newList);
        return newList;
    }
}
