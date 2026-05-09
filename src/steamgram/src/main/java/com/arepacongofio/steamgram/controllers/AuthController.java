package com.arepacongofio.steamgram.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.arepacongofio.steamgram.domain.requests.LoginRequest;
import com.arepacongofio.steamgram.domain.requests.UserRequest;
import com.arepacongofio.steamgram.domain.responses.LoginResponse;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.mappers.UserMapper;
import com.arepacongofio.steamgram.securization.config.JwtService;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Login and registration")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final IUserService userService;
    private final UserMapper userMapper;

    public AuthController(AuthenticationManager authenticationManager,
            JwtService jwtService,
            IUserService userService,
            UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Returns a JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid body"),
            @ApiResponse(responseCode = "404", description = "User not found"),
    })
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        User user = userService.getUserByNickname(req.getUsername());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        String token = jwtService.generateToken(auth);
        return ResponseEntity.ok(new LoginResponse(token, userMapper.toResponse(user)));
    }

    @PostMapping("/register")
    @Operation(summary = "Register", description = "Creates a new user and returns their data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid body"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest req) {
        UserResponse created = userService.createUser(req);
        if (created == null) {
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(201).body(created);
    }
}