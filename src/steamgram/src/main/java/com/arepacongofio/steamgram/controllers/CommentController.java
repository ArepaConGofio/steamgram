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
import com.arepacongofio.steamgram.domain.requests.CommentRequest;
import com.arepacongofio.steamgram.domain.responses.CommentResponse;
import com.arepacongofio.steamgram.entities.Comment;
import com.arepacongofio.steamgram.mappers.CommentMapper;
import com.arepacongofio.steamgram.service.interfaces.ICommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comment")
@Tag(name = "Comment", description = "Complete comment management")
public class CommentController implements IController<CommentResponse, CommentRequest, Integer> {

    ICommentService commentService;
    CommentMapper commentMapper;

    public CommentController(ICommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @Override
    @GetMapping
    @Operation(summary = "List comments", description = "Lists all comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<CommentResponse>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity
                .ok(commentMapper.toResponseList(commentService.findAll(PageRequest.of(page, pageSize))));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Find a Comment by their Id", description = "Find a Comment by their Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment found successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    public ResponseEntity<CommentResponse> findById(@PathVariable Integer id) {
        Comment comment = commentService.findById(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commentMapper.toResponse(comment));
    }

    @Override
    @PostMapping
    @Operation(summary = "Save a Comment", description = "Save a Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully")
    })
    public ResponseEntity<CommentResponse> save(@Valid @RequestBody CommentRequest entity) {
        return ResponseEntity.ok(commentMapper.toResponse(commentService.save(commentMapper.toEntity(entity))));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Comment", description = "Delete a Comment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

        boolean result = commentService.deleteById(id);

        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
