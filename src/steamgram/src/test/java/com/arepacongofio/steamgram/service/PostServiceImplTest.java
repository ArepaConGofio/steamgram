package com.arepacongofio.steamgram.service;

import com.arepacongofio.steamgram.domain.requests.LikeCreateRequest;
import com.arepacongofio.steamgram.domain.responses.LikePostResponse;
import com.arepacongofio.steamgram.entities.Like;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.repository.LikeJpaRepository;
import com.arepacongofio.steamgram.repository.PostJpaRepository;
import com.arepacongofio.steamgram.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostJpaRepository postRepository;

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private LikeJpaRepository likeJpaRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void toggleLikeUserOrPostNotFoundTest() {
        LikeCreateRequest request = new LikeCreateRequest();
        request.setIdUser(1);
        request.setIdPost(1);
        
        when(userJpaRepository.findById(1)).thenReturn(Optional.empty());
        
        LikePostResponse response = postService.toggleLike(request);
        
        assertNull(response);
    }

    @Test
    void toggleLikeAddLikeTest() {
        LikeCreateRequest request = new LikeCreateRequest();
        request.setIdUser(1);
        request.setIdPost(1);
        
        User user = new User(1);
        Post post = new Post(1);
        post.setLikes(new ArrayList<>());
        
        when(userJpaRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(likeJpaRepository.findByUserAndPost(user, post)).thenReturn(Optional.empty());
        
        LikePostResponse response = postService.toggleLike(request);
        
        assertNotNull(response);
        assertEquals(1, response.getIdPost());
        assertEquals(1, response.getIdUser());
        assertTrue(response.isLiked());
        
        verify(likeJpaRepository).save(any(Like.class));
        assertEquals(1, post.getLikes().size());
    }

    @Test
    void toggleLikeRemoveLikeTest() {
        LikeCreateRequest request = new LikeCreateRequest();
        request.setIdUser(1);
        request.setIdPost(1);
        
        User user = new User(1);
        Post post = new Post(1);
        Like like = new Like(user, post);
        post.setLikes(new ArrayList<>());
        post.getLikes().add(like);
        
        when(userJpaRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(likeJpaRepository.findByUserAndPost(user, post)).thenReturn(Optional.of(like));
        
        LikePostResponse response = postService.toggleLike(request);
        
        assertNotNull(response);
        assertEquals(1, response.getIdPost());
        assertEquals(1, response.getIdUser());
        assertFalse(response.isLiked());
        
        verify(likeJpaRepository).delete(like);
        assertEquals(0, post.getLikes().size());
    }

    @Test
    void saveNewPostTest() {
        Post post = new Post();
        assertNull(post.getPublicationDate());
        
        when(postRepository.save(post)).thenReturn(post);
        
        Post savedPost = postService.save(post);
        
        assertNotNull(savedPost.getPublicationDate());
    }
}
