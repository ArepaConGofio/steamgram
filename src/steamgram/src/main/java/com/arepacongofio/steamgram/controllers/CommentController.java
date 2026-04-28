package com.arepacongofio.steamgram.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.arepacongofio.steamgram.controllers.interfaces.IController;
import com.arepacongofio.steamgram.domain.requests.CommentCreateRequest;
import com.arepacongofio.steamgram.domain.responses.CommentResponse;
import com.arepacongofio.steamgram.entities.Comment;
import com.arepacongofio.steamgram.mappers.CommentMapper;
import com.arepacongofio.steamgram.service.interfaces.ICommentService;

public class CommentController implements IController<CommentResponse, CommentCreateRequest, Integer> {

    ICommentService commentService;
    CommentMapper commentMapper;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public ResponseEntity<List<CommentResponse>> findAll(@RequestParam int page, @RequestParam int pageSize) {
        return ResponseEntity
                .ok(commentMapper.toResponseList(commentService.findAll(PageRequest.of(page, pageSize))));
    }

    @Override
    public ResponseEntity<CommentResponse> findById(Integer id) {
        Comment comment = commentService.findById(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commentMapper.toResponse(comment));
    }

    @Override
    public ResponseEntity<CommentResponse> save(CommentCreateRequest entity) {
        return ResponseEntity.ok(commentMapper.toResponse(commentService.save(commentMapper.toEntity(entity))));
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        
        boolean result = commentService.deleteById(id);

        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
