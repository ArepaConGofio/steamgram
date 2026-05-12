package com.arepacongofio.steamgram.service;

import com.arepacongofio.steamgram.domain.requests.SaveGameRequest;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.repository.GameJpaRepository;
import com.arepacongofio.steamgram.repository.PostJpaRepository;
import com.arepacongofio.steamgram.repository.ReviewJpaRepository;
import com.arepacongofio.steamgram.repository.UserJpaRepository;
import com.arepacongofio.steamgram.service.interfaces.IDeveloperService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @Mock
    private GameJpaRepository gameRepository;

    @Mock
    private PostJpaRepository postRepository;

    @Mock
    private ReviewJpaRepository reviewRepository;

    @Mock
    private IDeveloperService devsService;

    @Mock
    private UserJpaRepository userJpaRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    void getGamePostsGameNotFoundTest() {
        when(gameRepository.findById(1)).thenReturn(Optional.empty());
        List<Post> posts = gameService.getGamePosts(PageRequest.of(0, 10), 1);
        assertTrue(posts.isEmpty());
    }

    @Test
    void getGamePostsSuccessTest() {
        Game game = new Game(1);
        List<Post> mockPosts = List.of(new Post());
        Pageable pageable = PageRequest.of(0, 10);
        
        when(gameRepository.findById(1)).thenReturn(Optional.of(game));
        when(postRepository.findByGame(pageable, game)).thenReturn(mockPosts);
        
        List<Post> posts = gameService.getGamePosts(pageable, 1);
        
        assertEquals(1, posts.size());
    }

    @Test
    void saveGameIntoProfileUserOrGameNotFoundTest() {
        SaveGameRequest request = new SaveGameRequest();
        request.setUserId(1);
        request.setGameId(1);
        
        when(userJpaRepository.findById(1)).thenReturn(Optional.empty());
        
        Game result = gameService.saveGameIntoProfile(request);
        assertNull(result);
    }

    @Test
    void saveGameIntoProfileAddGameTest() {
        SaveGameRequest request = new SaveGameRequest();
        request.setUserId(1);
        request.setGameId(1);
        
        User user = new User(1);
        user.setGames(new ArrayList<>());
        Game game = new Game(1);
        
        when(userJpaRepository.findById(1)).thenReturn(Optional.of(user));
        when(gameRepository.findById(1)).thenReturn(Optional.of(game));
        
        Game result = gameService.saveGameIntoProfile(request);
        
        assertNotNull(result);
        assertTrue(user.getGames().contains(game));
        verify(userJpaRepository).save(user);
    }
    
    @Test
    void saveGameIntoProfileRemoveGameTest() {
        SaveGameRequest request = new SaveGameRequest();
        request.setUserId(1);
        request.setGameId(1);
        
        User user = new User(1);
        Game game = new Game(1);
        user.setGames(new ArrayList<>());
        user.getGames().add(game);
        
        when(userJpaRepository.findById(1)).thenReturn(Optional.of(user));
        when(gameRepository.findById(1)).thenReturn(Optional.of(game));
        
        Game result = gameService.saveGameIntoProfile(request);
        
        assertNotNull(result);
        assertFalse(user.getGames().contains(game));
        verify(userJpaRepository).save(user);
    }

    @Test
    void getGameReviewsGameNotFoundTest() {
        when(gameRepository.findById(1)).thenReturn(Optional.empty());
        List<Review> reviews = gameService.getGameReviews(PageRequest.of(0, 10), 1);
        assertTrue(reviews.isEmpty());
    }

    @Test
    void getGameReviewsSuccessTest() {
        Game game = new Game(1);
        List<Review> mockReviews = List.of(new Review());
        Pageable pageable = PageRequest.of(0, 10);
        
        when(gameRepository.findById(1)).thenReturn(Optional.of(game));
        when(reviewRepository.findByGame(pageable, game)).thenReturn(mockReviews);
        
        List<Review> reviews = gameService.getGameReviews(pageable, 1);
        
        assertEquals(1, reviews.size());
    }

    @Test
    void getGameByIgdbIdErrorTest() {
        assertThrows(org.springframework.web.server.ResponseStatusException.class, () -> {
            gameService.getGameByIgdbId("invalid_id");
        });
    }

    @Test
    void getGameByIgdbIdTest() {
        Game localGame = new Game(1);
        when(gameRepository.findByIdIgdb(123)).thenReturn(Optional.of(localGame));
        
        Game result = gameService.getGameByIgdbId("123");
        
        assertNotNull(result);
        assertEquals(localGame, result);
    }
}
