package com.arepacongofio.steamgram.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arepacongofio.steamgram.controllers.interfaces.IController;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.domain.requests.UserCreateRequest;
import com.arepacongofio.steamgram.mappers.UserMapper;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Complete user management")
public class UserController implements IController<UserResponse,UserCreateRequest, Integer> {

    IUserService userService;
    UserMapper userMapper;
    
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/users/")
    @Operation(summary = "List users", description = "Lists all users")
    public ResponseEntity<List<UserResponse>> findAll(@RequestParam int page, @RequestParam(value = "10") int pageSize) {
        return ResponseEntity.ok(userMapper.toResponseList(userService.findAll(PageRequest.of(page, pageSize))));
    }

    @Override
    @GetMapping("/user/{id}")
    @Operation(summary = "Find a User by their Id", description = "Find a User by their Id")
    public ResponseEntity<UserResponse> findById(@Valid @PathVariable Integer id) {
        UserResponse response = userMapper.toResponse(userService.findById(id));
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    @Operation(summary = "Save a User", description = "Save a User")
    public ResponseEntity<UserResponse> save(@Valid UserCreateRequest user) {
        return ResponseEntity.ok(userMapper.toResponse(userService.save(userMapper.toEntity(user))));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete User", description = "Delete an User by ID")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer id) {
        if (!userService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
