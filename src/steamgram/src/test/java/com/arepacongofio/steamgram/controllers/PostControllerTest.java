package com.arepacongofio.steamgram.controllers;

import com.arepacongofio.steamgram.domain.requests.LikeCreateRequest;
import com.arepacongofio.steamgram.domain.requests.PostRequest;
import com.arepacongofio.steamgram.domain.responses.LikePostResponse;
import com.arepacongofio.steamgram.domain.responses.PostResponse;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.mappers.PostMapper;
import com.arepacongofio.steamgram.service.interfaces.IPostService;

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
class PostControllerTest {

    @Mock
    private IPostService postService;

    @Mock
    private PostMapper postMapper;

    @InjectMocks
    private PostController postController;

    private List<Post> posts;
    private List<PostResponse> responses;
    private Post post;
    private PostResponse postResponse;
    private PostRequest request;
    private LikeCreateRequest likeRequest;
    private Integer id;

    @BeforeEach
    void setUp() {
        posts = List.of(new Post());
        responses = List.of(new PostResponse());
        post = new Post();
        postResponse = new PostResponse();
        request = new PostRequest();
        likeRequest = new LikeCreateRequest();
        id = 1;
    }

    @Test
    void findAllTest() {
        when(postService.findAll(any(PageRequest.class))).thenReturn(posts);
        when(postMapper.toResponseList(posts)).thenReturn(responses);
        
        ResponseEntity<List<PostResponse>> response = postController.findAll(0, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findByIdNotFoundTest() {
        when(postService.findById(id)).thenReturn(null);
        ResponseEntity<PostResponse> response = postController.findById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void saveTest() {
        
        when(postMapper.toEntity(request)).thenReturn(post);
        when(postService.save(post)).thenReturn(post);
        when(postMapper.toResponse(post)).thenReturn(postResponse);
        
        ResponseEntity<PostResponse> response = postController.save(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteByIdSuccessTest() {
        when(postService.deleteById(id)).thenReturn(true);
        ResponseEntity<Void> response = postController.deleteById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void toggleLikeNotFoundTest() {
        when(postService.toggleLike(likeRequest)).thenReturn(null);
        ResponseEntity<LikePostResponse> response = postController.toggleLike(likeRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
