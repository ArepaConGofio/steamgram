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
import com.arepacongofio.steamgram.domain.requests.CommentRequest;
import com.arepacongofio.steamgram.domain.responses.CommentResponse;
import com.arepacongofio.steamgram.entities.Comment;
import com.arepacongofio.steamgram.mappers.CommentMapper;
import com.arepacongofio.steamgram.service.interfaces.ICommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/comment")
@Tag(name = "Comment", description = "Complete comment management")
public class CommentController implements IController<CommentResponse, CommentRequest, Integer> {

    ICommentService commentService;
    CommentMapper commentMapper;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @GetMapping
    @Operation(summary = "List comments", description = "Lists all comments")
    public ResponseEntity<List<CommentResponse>> findAll(@RequestParam int page, @RequestParam int pageSize) {
        return ResponseEntity
                .ok(commentMapper.toResponseList(commentService.findAll(PageRequest.of(page, pageSize))));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Find a Comment by their Id", description = "Find a Comment by their Id")
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
    public ResponseEntity<CommentResponse> save(CommentRequest entity) {
        return ResponseEntity.ok(commentMapper.toResponse(commentService.save(commentMapper.toEntity(entity))));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Comment", description = "Delete a Comment by ID")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

        boolean result = commentService.deleteById(id);

        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
