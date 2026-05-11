package com.arepacongofio.steamgram.controllers;

import com.arepacongofio.steamgram.domain.requests.LoginRequest;
import com.arepacongofio.steamgram.domain.requests.UserRequest;
import com.arepacongofio.steamgram.domain.responses.LoginResponse;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.mappers.UserMapper;
import com.arepacongofio.steamgram.securization.config.JwtService;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private IUserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AuthController authController;

    UserRequest registerRequest;
    LoginRequest loginRequest;
    UserResponse userResponse;
    User user;
    
    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest();
        loginRequest.setUsername("user");
        loginRequest.setPassword("pass");
        user = new User();
        userResponse = new UserResponse();
        registerRequest = new UserRequest();
    }

    @Test
    void loginUserNotFoundTest() {

        when(userService.getUserByNickname("user")).thenReturn(null);

        ResponseEntity<LoginResponse> response = authController.login(loginRequest);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void loginSuccessTest() {

        Authentication auth = mock(Authentication.class);

        when(userService.getUserByNickname("user")).thenReturn(user);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(jwtService.generateToken(auth)).thenReturn("token");
        when(userMapper.toResponse(user)).thenReturn(userResponse);

        ResponseEntity<LoginResponse> response = authController.login(loginRequest);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("token", response.getBody().getToken());
    }

    @Test
    void registerConflictTest() {
        when(userService.createUser(registerRequest)).thenReturn(null);

        ResponseEntity<UserResponse> response = authController.register(registerRequest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void registerSuccessTest() {
        when(userService.createUser(registerRequest)).thenReturn(userResponse);

        ResponseEntity<UserResponse> response = authController.register(registerRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponse, response.getBody());
    }
}
