package com.arepacongofio.steamgram.service;

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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
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
    private Integer id1;
    private Integer id2;
    private User user;
    private Game game1;
    private Game game2;
    private Post post1;
    private Post post2;

    @BeforeEach
    void setUp() {      
        id1 = 1;
        id2 = 2;

        userService.setUserMapper(userMapper);

        userRequest = new UserRequest("name", "nickname", "test@gmail.com", "pass");

        userEditRequest = new UserEditRequest();
        userEditRequest.setId(id1);
        userEditRequest.setName("NewName");
        userEditRequest.setAvatarUrl("url");

        user = new User(id1);
        game1 = new Game(id1);
        game2 = new Game(id2);
        post1 = new Post(id1);
        post2 = new Post(id2);
    }

    @Test
    void createUserUserExistsTest() {
        when(userRepository.existsByEmail("test@gmail.com")).thenReturn(true);
        
        UserResponse response = userService.createUser(userRequest);
        
        assertNull(response);
    }

    @Test
    void createUserSuccessTest() {
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
        when(userRepository.existsById(id1)).thenReturn(false);
        List<Game> games = userService.getUserGames(id1);
        assertTrue(games.isEmpty());
    }

    @Test
    void getUserGamesSuccessTest() {

        user.setGames(List.of(game1, game2));
        
        when(userRepository.existsById(id1)).thenReturn(true);
        when(userRepository.findById(id1)).thenReturn(Optional.of(user));
        
        List<Game> games = userService.getUserGames(id1);
        
        assertEquals(2, games.size());
        assertEquals(game2, games.get(0));
        assertEquals(game1, games.get(1));
    }

    @Test
    void editUserNotFoundTest() {
        userEditRequest.setId(id1);
        when(userRepository.findById(id1)).thenReturn(Optional.empty());
        
        UserResponse response = userService.editUser(userEditRequest);
        assertNull(response);
    }

    @Test
    void editUserSuccessTest() {
        UserResponse userResponse = new UserResponse();
        
        when(userRepository.findById(id1)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(userResponse);
        
        UserResponse response = userService.editUser(userEditRequest);
        
        assertNotNull(response);
        assertEquals("NewName", user.getName());
        assertEquals("url", user.getAvatarUrl());
    }

    @Test
    void getUserPostsNotFoundTest() {
        when(userRepository.existsById(id1)).thenReturn(false);
        List<Post> posts = userService.getUserPosts(id1);
        assertTrue(posts.isEmpty());
    }

    @Test
    void getUserPostsTest() {
        user.setPosts(List.of(post1, post2));
        
        when(userRepository.existsById(id1)).thenReturn(true);
        when(userRepository.findById(id1)).thenReturn(Optional.of(user));
        
        List<Post> posts = userService.getUserPosts(id1);
        
        assertEquals(2, posts.size());
        assertEquals(post2, posts.get(0));
        assertEquals(post1, posts.get(1));
    }

    @Test
    void getUserReviewsNotFoundTest() {
        when(userRepository.existsById(id1)).thenReturn(false);
        List<Review> reviews = userService.getUserReviews(id1);
        assertTrue(reviews.isEmpty());
    }

    @Test
    void getUserReviewsSuccessTest() {
        Review review1 = new Review(id1);
        Review review2 = new Review(id2);
        user.setReviews(List.of(review1, review2));
        
        when(userRepository.existsById(id1)).thenReturn(true);
        when(userRepository.findById(id1)).thenReturn(Optional.of(user));
        
        List<Review> reviews = userService.getUserReviews(id1);
        
        assertEquals(2, reviews.size());
        assertEquals(review2, reviews.get(0));
        assertEquals(review1, reviews.get(1));
    }

    @Test
    void getUserByNicknameTest() {
        when(userRepository.findByNickname("nickname")).thenReturn(Optional.of(user));
        
        User result = userService.getUserByNickname("nickname");
        
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void findUserByNameTest() {
        UserFindRequest request = new UserFindRequest();
        request.setName("name");
        request.setNickname("nickname");
        
        UserResponse response = new UserResponse();
        PageRequest pageRequest = PageRequest.of(0, 10);
        
        when(userRepository.findByNameAndNickname(pageRequest, "name", "nickname")).thenReturn(List.of(user));
        when(userMapper.toResponseList(List.of(user))).thenReturn(List.of(response));
        
        List<UserResponse> results = userService.findUserByName(pageRequest, request);
        
        assertEquals(1, results.size());
        assertEquals(response, results.get(0));
    }

    @Test
    void findUserByNameNullRequestTest() {
        List<UserResponse> results = userService.findUserByName(PageRequest.of(0, 10), null);
        assertTrue(results.isEmpty());
    }

    @Test
    void existsByNicknameTest() {
        when(userRepository.existsByNickname("nickname")).thenReturn(true);
        
        Boolean exists = userService.existsByNickname("nickname");
        
        assertTrue(exists);
        verify(userRepository).existsByNickname("nickname");
    }
}
