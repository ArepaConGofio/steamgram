package com.arepacongofio.steamgram.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arepacongofio.steamgram.domain.requests.UserCreateRequest;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.mappers.UserMapper;
import com.arepacongofio.steamgram.repository.UserJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

@Service
public class UserServiceImpl extends AbstractService<User, Integer> implements IUserService {

    private final UserJpaRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(JpaRepository<User, Integer> userJpaRepository, PasswordEncoder passwordEncoder,
            UserMapper userMapper,
            UserJpaRepository userRepository) {
        super(userJpaRepository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Error: El email it's already in use.");
        }
        if (userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("Error: El nickname it's already in use.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);

        return userMapper.toResponse(userRepository.save(userMapper.toEntity(request)));
    }
}
