package com.arepacongofio.steamgram.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arepacongofio.steamgram.controllers.interfaces.IController;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.domain.requests.UserEditRequest;
import com.arepacongofio.steamgram.domain.requests.UserFindRequest;
import com.arepacongofio.steamgram.domain.requests.UserRequest;
import com.arepacongofio.steamgram.mappers.UserMapper;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Complete user management")
public class UserController implements IController<UserResponse,UserRequest, Integer> {

    private final IUserService userService;
    private final UserMapper userMapper;
    
    public UserController(IUserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    @GetMapping
    @Operation(summary = "List users", description = "Lists all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Users listed successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<UserResponse>> findAll(@RequestParam(value = "0") int page, @RequestParam(value = "10") int pageSize) {
        return ResponseEntity.ok(userMapper.toResponseList(userService.findAll(PageRequest.of(page, pageSize))));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Find a User by their Id", description = "Find a User by their Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User found successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User saved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
    
    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User", description = "Delete an User by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer id) {
        if (!userService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping
    @Operation(summary = "Edit a user", description = "Edit a User")
    public ResponseEntity<UserResponse> edit(@Valid @RequestBody UserEditRequest user) {
        return ResponseEntity.ok(userService.editUser(user));
    }

    @GetMapping("/{id}/games")
    @Operation(summary = "Get user games", description = "Get user games by Id")
    public ResponseEntity<List<Game>> getUserGamesById(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserGames(new User(id)));
    }

    @GetMapping("/{id}/posts")
    @Operation(summary = "Get user posts", description = "Get user posts by Id")
    public ResponseEntity<List<Post>> getUserPostsById(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserPosts(new User(id)));
    }

    @GetMapping("/{id}/reviews")
    @Operation(summary = "Get user reviews", description = "Get user reviews by Id")
    public ResponseEntity<List<Review>> getUserReviewsById(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserReviews(new User(id)));
    }

    @GetMapping("/{id}/followers")
    @Operation(summary = "Get user followers", description = "Get user followers by Id")
    public ResponseEntity<List<User>> getUserFollowersById(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserFollowers(new User(id)));
    }

    @GetMapping("/{id}/following")
    @Operation(summary = "Get user following", description = "Get user following by Id")
    public ResponseEntity<List<User>> getUserFollowingById(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserFollows(new User(id)));
    }

    @GetMapping("/{username}/exists")
    @Operation(summary = "Check username availability", description = "Check if username already exists in database")
    public ResponseEntity<Boolean> checkUsernameAvailability(@Valid @PathVariable String username) {
        return ResponseEntity.ok(userService.checkExistsByName(username));
    }

    @GetMapping("/find")
    @Operation(summary = "Find user by username", description = "Find user by username")
    public ResponseEntity<List<User>> findUserByName(@Valid @RequestBody UserFindRequest request) {
        return ResponseEntity.ok(userService.findUserByName(request.getNickname(), request.getName()));
    }
}
