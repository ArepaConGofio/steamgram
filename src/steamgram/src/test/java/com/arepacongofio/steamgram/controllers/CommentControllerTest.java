package com.arepacongofio.steamgram.controllers;

import com.arepacongofio.steamgram.domain.requests.CommentRequest;
import com.arepacongofio.steamgram.domain.responses.CommentResponse;
import com.arepacongofio.steamgram.entities.Comment;
import com.arepacongofio.steamgram.mappers.CommentMapper;
import com.arepacongofio.steamgram.service.interfaces.ICommentService;

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
class CommentControllerTest {

    @Mock
    private ICommentService commentService;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentController commentController;

    private Integer id ;
    private List<Comment> comments;
    private List<CommentResponse> responses;
    private Comment comment;
    private CommentResponse commentResponse;
    private CommentRequest request;
    private Comment savedComment;

    @BeforeEach
    void setUp() {
        id = 1;
        comments = List.of(new Comment());
        responses = List.of(new CommentResponse());
        comment = new Comment();
        commentResponse = new CommentResponse();
        request = new CommentRequest();
        savedComment = new Comment();
    }

    @Test
    void findAllTest() {
        
        when(commentService.findAll(any(PageRequest.class))).thenReturn(comments);
        when(commentMapper.toResponseList(comments)).thenReturn(responses);
        
        ResponseEntity<List<CommentResponse>> response = commentController.findAll(0, 10);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responses, response.getBody());
    }

    @Test
    void findByIdNotFoundTest() {
        when(commentService.findById(id)).thenReturn(null);
        
        ResponseEntity<CommentResponse> response = commentController.findById(id);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void findByIdSuccessTest() {
        
        when(commentService.findById(id)).thenReturn(comment);
        when(commentMapper.toResponse(comment)).thenReturn(commentResponse);
        
        ResponseEntity<CommentResponse> response = commentController.findById(id);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentResponse, response.getBody());
    }

    @Test
    void saveTest() {
        
        when(commentMapper.toEntity(request)).thenReturn(comment);
        when(commentService.save(comment)).thenReturn(savedComment);
        when(commentMapper.toResponse(savedComment)).thenReturn(commentResponse);
        
        ResponseEntity<CommentResponse> response = commentController.save(request);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentResponse, response.getBody());
    }

    @Test
    void deleteByIdNotFoundTest() {
        when(commentService.deleteById(id)).thenReturn(false);
        
        ResponseEntity<Void> response = commentController.deleteById(id);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteByIdSuccessTest() {
        when(commentService.deleteById(id)).thenReturn(true);
        
        ResponseEntity<Void> response = commentController.deleteById(id);
        
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
