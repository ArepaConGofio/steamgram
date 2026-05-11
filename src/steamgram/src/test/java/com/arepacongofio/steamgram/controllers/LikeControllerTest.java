package com.arepacongofio.steamgram.controllers;

import com.arepacongofio.steamgram.domain.requests.LikeRequest;
import com.arepacongofio.steamgram.domain.responses.LikeResponse;
import com.arepacongofio.steamgram.entities.Like;
import com.arepacongofio.steamgram.mappers.LikeMapper;
import com.arepacongofio.steamgram.service.interfaces.ILikeService;

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
class LikeControllerTest {

    @Mock
    private ILikeService likeService;

    @Mock
    private LikeMapper likeMapper;

    @InjectMocks
    private LikeController likeController;

    private List<Like> likes;
    private List<LikeResponse> responses;
    private Like like;
    private LikeResponse likeResponse;
    private LikeRequest request;
    private Like savedLike;
    private Integer id;

    @BeforeEach
    void setUp() {
        likes = List.of(new Like());
        responses = List.of(new LikeResponse());
        like = new Like();
        likeResponse = new LikeResponse();
        request = new LikeRequest();
        savedLike = new Like();
        id = 1;
    }

    @Test
    void findAllTest() {
        
        when(likeService.findAll(any(PageRequest.class))).thenReturn(likes);
        when(likeMapper.toResponseList(likes)).thenReturn(responses);
        
        ResponseEntity<List<LikeResponse>> response = likeController.findAll(0, 10);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responses, response.getBody());
    }

    @Test
    void findByIdNotFoundTest() {
        when(likeService.findById(id)).thenReturn(null);
        
        ResponseEntity<LikeResponse> response = likeController.findById(1);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void saveTest() {
        
        when(likeMapper.toEntity(request)).thenReturn(like);
        when(likeService.save(like)).thenReturn(savedLike);
        when(likeMapper.toResponse(savedLike)).thenReturn(likeResponse);
        
        ResponseEntity<LikeResponse> response = likeController.save(request);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(likeResponse, response.getBody());
    }

    @Test
    void deleteByIdSuccessTest() {
        when(likeService.deleteById(id)).thenReturn(true);
        ResponseEntity<Void> response = likeController.deleteById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
