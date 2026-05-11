package com.arepacongofio.steamgram.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void emptyConstructorTest() {
        Post post = new Post();
        assertNull(post.getId());
    }

    @Test
    void idConstructorTest() {
        Post post = new Post(1);
        assertEquals(1, post.getId());
    }

    @Test
    void basicConstructorTest() {
        User user = new User();
        Game game = new Game();
        
        Post post = new Post(user, game, "Title", "Desc");
        
        assertEquals(user, post.getUser());
        assertEquals(game, post.getGame());
        assertEquals("Title", post.getTitle());
        assertEquals("Desc", post.getDescription());
        assertNotNull(post.getLikes());
        assertNotNull(post.getComments());
        assertNotNull(post.getPublicationDate());
    }

    @Test
    void completeConstructorTest() {
        User user = new User();
        Game game = new Game();
        List<Like> likes = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        
        Post post = new Post(1, user, game, "Title", "Desc", likes, comments);
        
        assertEquals(1, post.getId());
        assertEquals(user, post.getUser());
        assertEquals(game, post.getGame());
        assertEquals("Title", post.getTitle());
        assertEquals("Desc", post.getDescription());
        assertEquals(likes, post.getLikes());
        assertEquals(comments, post.getComments());
        assertNotNull(post.getPublicationDate());
    }

    @Test
    void gettersAndSettersTest() {
        Post post = new Post();
        post.setId(1);
        assertEquals(1, post.getId());
        
        User user = new User();
        post.setUser(user);
        assertEquals(user, post.getUser());
        
        Game game = new Game();
        post.setGame(game);
        assertEquals(game, post.getGame());
        
        post.setTitle("Title");
        assertEquals("Title", post.getTitle());
        
        post.setDescription("Desc");
        assertEquals("Desc", post.getDescription());
        
        List<Like> likes = new ArrayList<>();
        post.setLikes(likes);
        assertEquals(likes, post.getLikes());
        
        List<Comment> comments = new ArrayList<>();
        post.setComments(comments);
        assertEquals(comments, post.getComments());
        
        LocalDateTime now = LocalDateTime.now();
        post.setPublicationDate(now);
        assertEquals(now, post.getPublicationDate());
    }

    @Test
    void equalsAndHashCodeTest() {
        Post post1 = new Post(1);
        Post post2 = new Post(1);
        Post post3 = new Post(2);
        
        assertEquals(post1, post1);
        assertEquals(post1, post2);
        assertNotEquals(post1, post3);
        assertNotEquals(post1, new Object());
        
        assertEquals(post1.hashCode(), post2.hashCode());
        assertNotEquals(post1.hashCode(), post3.hashCode());
    }
}
