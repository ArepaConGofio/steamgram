package com.arepacongofio.steamgram.controllers;

import com.arepacongofio.steamgram.domain.requests.UserEditRequest;
import com.arepacongofio.steamgram.domain.requests.UserRequest;
import com.arepacongofio.steamgram.domain.responses.GameDetailsResponse;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.mappers.GameMapper;
import com.arepacongofio.steamgram.mappers.PostMapper;
import com.arepacongofio.steamgram.mappers.ReviewMapper;
import com.arepacongofio.steamgram.mappers.UserMapper;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private IUserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private GameMapper gameMapper;

    @Mock
    private PostMapper postMapper;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private UserController userController;

    private List<User> users;
    private List<UserResponse> responses;
    private UserResponse userResponse;
    private UserEditRequest userEditRequest;
    private UserRequest userRequest;
    private Integer id;

    @BeforeEach
    void setUp() {
        users = List.of(new User());
        responses = List.of(new UserResponse());
        id = 1;
        userResponse = new UserResponse();
        userEditRequest = new UserEditRequest();
        userRequest = new UserRequest();
    }

    @Test
    void findAllTest() {
        when(userService.findAll(any(PageRequest.class))).thenReturn(users);
        when(userMapper.toResponseList(users)).thenReturn(responses);
        
        ResponseEntity<List<UserResponse>> response = userController.findAll(0, 10);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findByIdNotFoundTest() {
        when(userService.findById(id)).thenReturn(null);
        ResponseEntity<UserResponse> response = userController.findById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void saveTest() {
        when(userService.createUser(userRequest)).thenReturn(userResponse);
        
        ResponseEntity<UserResponse> response = userController.save(userRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteByIdSuccessTest() {
        when(userService.deleteById(id)).thenReturn(true);
        ResponseEntity<Void> response = userController.deleteById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void editUserNotFoundTest() {
        when(userService.editUser(userEditRequest)).thenReturn(null);
        
        ResponseEntity<UserResponse> response = userController.editUser(userEditRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getUserGamesByIdTest() {
        when(userService.getUserGames(id)).thenReturn(List.of());
        when(gameMapper.toDetailsResponseList(any())).thenReturn(List.of());
        
        ResponseEntity<List<GameDetailsResponse>> response = userController.getUserGamesById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
