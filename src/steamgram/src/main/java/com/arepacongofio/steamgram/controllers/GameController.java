package com.arepacongofio.steamgram.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arepacongofio.steamgram.controllers.interfaces.IController;
import com.arepacongofio.steamgram.domain.requests.GameRequest;
import com.arepacongofio.steamgram.domain.requests.GameSearchRequest;
import com.arepacongofio.steamgram.domain.requests.SaveGameRequest;
import com.arepacongofio.steamgram.domain.responses.GameDetailsResponse;
import com.arepacongofio.steamgram.domain.responses.GameResponse;
import com.arepacongofio.steamgram.domain.responses.PostResponse;
import com.arepacongofio.steamgram.domain.responses.ReviewResponse;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.mappers.GameMapper;
import com.arepacongofio.steamgram.mappers.PostMapper;
import com.arepacongofio.steamgram.mappers.ReviewMapper;
import com.arepacongofio.steamgram.service.interfaces.IGameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/game")
@Tag(name = "Game", description = "Complete game management")
public class GameController implements IController<GameDetailsResponse, GameRequest, Integer> {

    private final IGameService gameService;
    private final GameMapper gameMapper;
    private final PostMapper postMapper;
    private final ReviewMapper reviewMapper;

    public GameController(IGameService gameService, GameMapper gameMapper, PostMapper postMapper, ReviewMapper reviewMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
        this.postMapper = postMapper;
        this.reviewMapper = reviewMapper;
    }

    @Override
    @GetMapping
    @Operation(summary = "List games", description = "Lists all games")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Games listed successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<GameDetailsResponse>> findAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(gameMapper.toDetailsResponseList(gameService.findAll(PageRequest.of(page, pageSize))));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Find a Game by its Id", description = "Find a Game by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game found successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<GameDetailsResponse> findById(@Valid @PathVariable Integer id) {
        com.arepacongofio.steamgram.entities.Game game = gameService.findById(id);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameMapper.toDetailsResponse(game));
    }

    @Override 
    @PostMapping
    @Operation(summary = "Save a Game", description = "Save a Game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game saved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<GameDetailsResponse> save(@Valid @RequestBody GameRequest gameRequest) {
        return ResponseEntity.ok(gameMapper.toDetailsResponse(gameService.save(gameMapper.toEntity(gameRequest))));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Game", description = "Delete a Game by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "G ame deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer id) {
        if (!gameService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("posts/{id}")
    @Operation(summary = "Get game posts", description = "Get game posts by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Posts listed successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<PostResponse>> getGamePosts(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize, @PathVariable Integer id) {
        return ResponseEntity.ok(postMapper.toResponseList(gameService.getGamePosts(PageRequest.of(page, pageSize), id)));
    }

    @GetMapping("reviews/{id}")
    @Operation(summary = "Get game reviews", description = "Get game reviews by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reviews listed successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<ReviewResponse>> getGameReviews(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize, @Valid @PathVariable Integer id) {
        return ResponseEntity.ok(reviewMapper.toResponseList(gameService.getGameReviews(PageRequest.of(page, pageSize), id)));
    }

    @PostMapping("/search")
    @Operation(summary = "Search game by title", description = "Search game by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Games listed successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<GameResponse>> searchGameByTitle(@RequestBody GameSearchRequest request) {
        return ResponseEntity.ok(gameMapper.toResponseList(gameService.findIgdbGamesByTitle(request.getTitle())));
    }

    @GetMapping("/igdb/{id}")
    @Operation(summary = "Get a Game by its IGDB Id", description = "Get a Game by its IGDB Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game found successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<GameDetailsResponse> getByIgdbId(@PathVariable String id) {
        com.arepacongofio.steamgram.entities.Game game = gameService.getGameByIgdbId(id);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameMapper.toDetailsResponse(game));
    }

    @PostMapping("/save/")
    @Operation(summary = "Save a game into personal library", description = "Save/release a game into/from personal library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game saved successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<GameDetailsResponse> toggleSaveGame(@Valid @RequestBody SaveGameRequest request) {
        Game game = gameService.saveGameIntoProfile(request);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameMapper.toDetailsResponse(game));
    }
}
