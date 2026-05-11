package com.arepacongofio.steamgram.service;

import com.arepacongofio.steamgram.domain.requests.UserEditRequest;
import com.arepacongofio.steamgram.domain.requests.UserRequest;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.mappers.UserMapper;
import com.arepacongofio.steamgram.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserJpaRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequest userRequest;
    private UserEditRequest userEditRequest;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        userService.setUserMapper(userMapper);

        userRequest = new UserRequest("name", "nickname", "test@gmail.com", "pass");

        userEditRequest = new UserEditRequest();
        userEditRequest.setId(1);
        userEditRequest.setName("NewName");
        userEditRequest.setAvatarUrl("url");
    }

    @Test
    void createUserUserExistsTest() {
        when(userRepository.existsByEmail("test@gmail.com")).thenReturn(true);
        
        UserResponse response = userService.createUser(userRequest);
        
        assertNull(response);
    }

    @Test
    void createUserSuccessTest() {
        User user = new User();
        UserResponse userResponse = new UserResponse();
        
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.existsByNickname(anyString())).thenReturn(false);
        when(userMapper.toEntity(userRequest)).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPass");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(userResponse);
        
        UserResponse response = userService.createUser(userRequest);
        
        assertNotNull(response);
        assertEquals("encodedPass", userRequest.getPassword());
    }

    @Test
    void getUserGamesNotFoundTest() {
        when(userRepository.existsById(1)).thenReturn(false);
        List<Game> games = userService.getUserGames(1);
        assertTrue(games.isEmpty());
    }

    @Test
    void getUserGamesSuccessTest() {
        User user = new User(1);
        Game game1 = new Game(1);
        Game game2 = new Game(2);
        user.setGames(List.of(game1, game2));
        
        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        
        List<Game> games = userService.getUserGames(1);
        
        assertEquals(2, games.size());
        assertEquals(game2, games.get(0));
        assertEquals(game1, games.get(1));
    }

    @Test
    void editUserNotFoundTest() {
        userEditRequest.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        
        UserResponse response = userService.editUser(userEditRequest);
        assertNull(response);
    }

    @Test
    void editUserSuccessTest() {
        User user = new User(1);
        UserResponse userResponse = new UserResponse();
        
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(userResponse);
        
        UserResponse response = userService.editUser(userEditRequest);
        
        assertNotNull(response);
        assertEquals("NewName", user.getName());
        assertEquals("url", user.getAvatarUrl());
    }
}
