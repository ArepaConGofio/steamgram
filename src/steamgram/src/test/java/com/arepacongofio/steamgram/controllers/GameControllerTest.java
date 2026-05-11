package com.arepacongofio.steamgram.controllers;

import com.arepacongofio.steamgram.domain.requests.GameRequest;
import com.arepacongofio.steamgram.domain.requests.SaveGameRequest;
import com.arepacongofio.steamgram.domain.responses.GameDetailsResponse;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.mappers.GameMapper;
import com.arepacongofio.steamgram.mappers.PostMapper;
import com.arepacongofio.steamgram.mappers.ReviewMapper;
import com.arepacongofio.steamgram.service.interfaces.IGameService;

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
class GameControllerTest {

    @Mock
    private IGameService gameService;

    @Mock
    private GameMapper gameMapper;

    @Mock
    private PostMapper postMapper;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private GameController gameController;

    private List<Game> games;
    private List<GameDetailsResponse> responses;
    private Game game;
    private GameDetailsResponse gameDetailsResponse;
    private GameRequest gameRequest;
    private SaveGameRequest saveGameRequest;
    private Game savedGame;

    @BeforeEach
    void setUp() {
        games = List.of(new Game());
        responses = List.of(new GameDetailsResponse());
        game = new Game();
        gameDetailsResponse = new GameDetailsResponse();
        gameRequest = new GameRequest();
        saveGameRequest = new SaveGameRequest();
        savedGame = new Game();
    }

    @Test
    void findAllTest() {
        
        when(gameService.findAll(any(PageRequest.class))).thenReturn(games);
        when(gameMapper.toDetailsResponseList(games)).thenReturn(responses);
        
        ResponseEntity<List<GameDetailsResponse>> response = gameController.findAll(0, 10);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responses, response.getBody());
    }

    @Test
    void findByIdNotFoundTest() {
        when(gameService.findById(1)).thenReturn(null);
        ResponseEntity<GameDetailsResponse> response = gameController.findById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void saveTest() {
        
        when(gameMapper.toEntity(gameRequest)).thenReturn(game);
        when(gameService.save(game)).thenReturn(savedGame);
        when(gameMapper.toDetailsResponse(savedGame)).thenReturn(gameDetailsResponse);
        
        ResponseEntity<GameDetailsResponse> response = gameController.save(gameRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteByIdSuccessTest() {
        when(gameService.deleteById(1)).thenReturn(true);
        ResponseEntity<Void> response = gameController.deleteById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getByIgdbIdNotFoundTest() {
        when(gameService.getGameByIgdbId("123")).thenReturn(null);
        ResponseEntity<GameDetailsResponse> response = gameController.getByIgdbId("123");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void toggleSaveGameNotFoundTest() {
        when(gameService.saveGameIntoProfile(saveGameRequest)).thenReturn(null);
        ResponseEntity<GameDetailsResponse> response = gameController.toggleSaveGame(saveGameRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
