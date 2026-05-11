package com.arepacongofio.steamgram.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class LikeTest {

    @Test
    void emptyConstructorTest() {
        Like like = new Like();
        assertNull(like.getId());
    }

    @Test
    void idConstructorTest() {
        Like like = new Like(1);
        assertEquals(1, like.getId());
    }

    @Test
    void basicConstructorTest() {
        User user = new User();
        Post post = new Post();
        
        Like like = new Like(user, post);
        
        assertEquals(user, like.getUser());
        assertEquals(post, like.getPost());
        assertNotNull(like.getDate());
    }

    @Test
    void completeConstructorTest() {
        User user = new User();
        Post post = new Post();
        
        Like like = new Like(1, user, post);
        
        assertEquals(1, like.getId());
        assertEquals(user, like.getUser());
        assertEquals(post, like.getPost());
        assertNotNull(like.getDate());
    }

    @Test
    void gettersAndSettersTest() {
        Like like = new Like();
        like.setId(1);
        assertEquals(1, like.getId());
        
        User user = new User();
        like.setUser(user);
        assertEquals(user, like.getUser());
        
        Post post = new Post();
        like.setPost(post);
        assertEquals(post, like.getPost());
        
        LocalDateTime now = LocalDateTime.now();
        like.setDate(now);
        assertEquals(now, like.getDate());
    }

    @Test
    void equalsAndHashCodeTest() {
        Like like1 = new Like(1);
        Like like2 = new Like(1);
        Like like3 = new Like(2);
        
        assertEquals(like1, like1);
        assertEquals(like1, like2);
        assertNotEquals(like1, like3);
        assertNotEquals(like1, new Object());
        
        assertEquals(like1.hashCode(), like2.hashCode());
        assertNotEquals(like1.hashCode(), like3.hashCode());
    }
}
