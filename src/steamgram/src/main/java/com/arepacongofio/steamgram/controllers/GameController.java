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
import com.arepacongofio.steamgram.domain.responses.GameGeneralResponse;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.mappers.GameMapper;
import com.arepacongofio.steamgram.service.interfaces.IGameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/game")
@Tag(name = "Game", description = "Complete game management")
public class GameController implements IController<GameGeneralResponse, GameRequest, Integer> {

    private final IGameService gameService;
    private final GameMapper gameMapper;

    public GameController(IGameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @Override
    @GetMapping
    @Operation(summary = "List games", description = "Lists all games")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Games listed successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<GameGeneralResponse>> findAll(@RequestParam(value = "0") int page,
            @RequestParam(value = "10") int pageSize) {
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
    public ResponseEntity<GameGeneralResponse> findById(@Valid @PathVariable Integer id) {
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
    public ResponseEntity<GameGeneralResponse> save(@Valid @RequestBody GameRequest gameRequest) {
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

    @GetMapping("/{id}/posts")
    @Operation(summary = "Get game posts", description = "Get game posts by ID")
    public ResponseEntity<List<Post>> getGamePosts(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(gameService.getGamePosts(new Game(id)));
    }

    @GetMapping("/{id}/reviews")
    @Operation(summary = "Get game reviews", description = "Get game reviews by ID")
    public ResponseEntity<List<Review>> getGameReviews(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(gameService.getGameReviews(new Game(id)));
    }

    @GetMapping("/find")
    @Operation(summary = "Find game by title", description = "Find game by title")
    public ResponseEntity<List<Game>> findGameByTitle(@Valid @PathVariable String title) {
        return ResponseEntity.ok(gameService.findIgdbGamesByTitle(title));
    }

    @GetMapping("/igdb/{id}")
    @Operation(summary = "Find a Game by its IGDB Id", description = "Find a Game by its IGDB Id")
    public ResponseEntity<GameGeneralResponse> getByIgdbId(@Valid @PathVariable String id) {
        com.arepacongofio.steamgram.entities.Game game = gameService.getGameByIgdbId(id);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameMapper.toDetailsResponse(game));
    }
}
