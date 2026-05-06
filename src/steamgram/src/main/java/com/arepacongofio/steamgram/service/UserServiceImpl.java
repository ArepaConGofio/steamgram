package com.arepacongofio.steamgram.service;

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

@Service
public class UserServiceImpl extends AbstractService<User, Integer> implements IUserService {

    private UserJpaRepository userRepository;
    private UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(JpaRepository<User, Integer> userJpaRepository, PasswordEncoder passwordEncoder) {
        super(userJpaRepository);
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public List<Game> getUserGames(Integer id) {
        if (id == null || !existsById(id)) {
            return List.of();
        }
        User user = findById(id);
        return user.getGames();
    }

    @Override
    public List<Post> getUserPosts(Integer id) {
        if (id == null || !existsById(id)) {
            return List.of();
        }
        User user = findById(id);
        return user.getPosts();
    }

    @Override
    public List<Review> getUserReviews(Integer id) {
        if (id == null || !existsById(id)) {
            return List.of();
        }
        User user = findById(id);
        return user.getReviews();
    }

    @Override
    public List<User> getUserFollows(Integer id) {
        if (id == null || !existsById(id)) {
            return List.of();
        }
        User user = findById(id);
        return user.getFollows();
    }

    @Override
    public List<User> getUserFollowers(Integer id) {
        if (id == null || !existsById(id)) {
            return List.of();
        }
        User user = findById(id);
        return user.getFollowers();
    }

    @Override
    public UserResponse editUser(UserEditRequest request) {

        User user = userRepository.findById(request.getId()).orElse(null);
        if (user == null) {
            return null;
        }

        user.setNickname(request.getNickname());
        user.setName(request.getName());
        user.setAvatarUrl(request.getAvatarUrl());

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> findUserByName(Pageable pageable, UserFindRequest request) {
        if (request == null) {
            return List.of();
        }
        return userMapper.toResponseList(
                userRepository.findUserByNameAndNickname(pageable, request.getName(), request.getNickname()));
    }

    @Override
    public Boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
