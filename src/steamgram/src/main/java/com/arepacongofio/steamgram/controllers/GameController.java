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
import com.arepacongofio.steamgram.domain.responses.GameCreationResponse;
import com.arepacongofio.steamgram.mappers.GameMapper;
import com.arepacongofio.steamgram.service.interfaces.IGameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/game")
@Tag(name = "Game", description = "Complete game management")
public class GameController implements IController<GameCreationResponse, GameRequest, Integer> {

    private final IGameService gameService;
    private final GameMapper gameMapper;

    public GameController(IGameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @Override
    @GetMapping
    @Operation(summary = "List games", description = "Lists all games")
    public ResponseEntity<List<GameCreationResponse>> findAll(@RequestParam int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(gameMapper.toResponseList(gameService.findAll(PageRequest.of(page, pageSize))));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Find a Game by its Id", description = "Find a Game by its Id")
    public ResponseEntity<GameCreationResponse> findById(@Valid @PathVariable Integer id) {
        com.arepacongofio.steamgram.entities.Game game = gameService.findById(id);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameMapper.toResponse(game));
    }

    @Override
    @PostMapping
    @Operation(summary = "Save a Game", description = "Save a Game")
    public ResponseEntity<GameCreationResponse> save(@Valid @RequestBody GameRequest gameRequest) {
        return ResponseEntity.ok(gameMapper.toResponse(gameService.save(gameMapper.toEntity(gameRequest))));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Game", description = "Delete a Game by ID")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer id) {
        if (!gameService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
