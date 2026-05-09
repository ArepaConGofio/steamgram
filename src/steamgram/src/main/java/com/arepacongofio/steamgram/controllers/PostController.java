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
import com.arepacongofio.steamgram.domain.requests.LikeCreateRequest;
import com.arepacongofio.steamgram.domain.requests.PostRequest;
import com.arepacongofio.steamgram.domain.responses.PostResponse;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.mappers.PostMapper;
import com.arepacongofio.steamgram.service.interfaces.IPostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
@Tag(name = "Post", description = "Complete post management")
public class PostController implements IController<PostResponse, PostRequest, Integer> {

    private final IPostService postService;
    private final PostMapper postMapper;

    public PostController(IPostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @Override
    @GetMapping
    @Operation(summary = "List posts", description = "Lists all posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Posts listed successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<PostResponse>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(postMapper.toResponseList(postService.findAll(PageRequest.of(page, pageSize))));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Find a Post by its Id", description = "Find a Post by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post found successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<PostResponse> findById(@Valid @PathVariable Integer id) {
        com.arepacongofio.steamgram.entities.Post post = postService.findById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postMapper.toResponse(post));
    }

    @Override
    @PostMapping
    @Operation(summary = "Save a Post", description = "Save a Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post saved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<PostResponse> save(@Valid @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(postMapper.toResponse(postService.save(postMapper.toEntity(postRequest))));
    }

    @PostMapping("/like")
    @Operation(summary = "Toggle a like on a Post", description = "Like a post if it doesn't have a like from the user, if it has one, it removes it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Like toggled successfully"),
            @ApiResponse(responseCode = "404", description = "Post or user not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<PostResponse> toggleLike(@Valid @RequestBody LikeCreateRequest request) {
        Post post = postService.toggleLike(request);
        if(post == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postMapper.toResponse(post));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Post", description = "Delete a Post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer id) {
        if (!postService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
