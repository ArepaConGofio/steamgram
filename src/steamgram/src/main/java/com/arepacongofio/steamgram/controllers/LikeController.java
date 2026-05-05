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
import com.arepacongofio.steamgram.domain.requests.LikeRequest;
import com.arepacongofio.steamgram.domain.responses.LikeResponse;
import com.arepacongofio.steamgram.mappers.LikeMapper;
import com.arepacongofio.steamgram.service.interfaces.ILikeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/like")
@Tag(name = "Like", description = "Complete like management")
public class LikeController implements IController<LikeResponse, LikeRequest, Integer> {

    private final ILikeService likeService;
    private final LikeMapper likeMapper;

    public LikeController(ILikeService likeService, LikeMapper likeMapper) {
        this.likeService = likeService;
        this.likeMapper = likeMapper;
    }

    @Override
    @GetMapping
    @Operation(summary = "List likes", description = "Lists all likes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Likes listed successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<LikeResponse>> findAll(@RequestParam(value = "0") int page,
            @RequestParam(value = "10") int pageSize) {
        return ResponseEntity.ok(likeMapper.toResponseList(likeService.findAll(PageRequest.of(page, pageSize))));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Find a Like by its Id", description = "Find a Like by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Like found successfully"),
            @ApiResponse(responseCode = "404", description = "Like not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<LikeResponse> findById(@Valid @PathVariable Integer id) {
        com.arepacongofio.steamgram.entities.Like like = likeService.findById(id);
        if (like == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(likeMapper.toResponse(like));
    }

    @Override
    @PostMapping
    @Operation(summary = "Save a Like", description = "Save a Like")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Like saved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<LikeResponse> save(@Valid @RequestBody LikeRequest likeRequest) {
        return ResponseEntity.ok(likeMapper.toResponse(likeService.save(likeMapper.toEntity(likeRequest))));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Like", description = "Delete a Like by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Like deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Like not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer id) {
        if (!likeService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
