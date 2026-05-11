package com.arepacongofio.steamgram.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    void emptyConstructorTest() {
        Comment comment = new Comment();
        assertNull(comment.getId());
    }

    @Test
    void idConstructorTest() {
        Comment comment = new Comment(1);
        assertEquals(1, comment.getId());
    }

    @Test
    void basicConstructorTest() {
        User user = new User();
        user.setNickname("testUser");
        Post post = new Post();
        post.setTitle("testPost");
        
        Comment comment = new Comment(user, post, "Great post!");
        
        assertEquals(user, comment.getUser());
        assertEquals(post, comment.getPost());
        assertEquals("Great post!", comment.getText());
        assertNotNull(comment.getDate());
    }

    @Test
    void completeConstructorTest() {
        User user = new User();
        user.setNickname("testUser");
        Post post = new Post();
        post.setTitle("testPost");
        
        Comment comment = new Comment(1, user, post, "Great post!");
        
        assertEquals(1, comment.getId());
        assertEquals(user, comment.getUser());
        assertEquals(post, comment.getPost());
        assertEquals("Great post!", comment.getText());
        assertNotNull(comment.getDate());
    }

    @Test
    void gettersAndSettersTest() {
        Comment comment = new Comment();
        comment.setId(2);
        assertEquals(2, comment.getId());
        
        User user = new User();
        comment.setUser(user);
        assertEquals(user, comment.getUser());
        
        Post post = new Post();
        comment.setPost(post);
        assertEquals(post, comment.getPost());
        
        comment.setText("Hello");
        assertEquals("Hello", comment.getText());
        
        LocalDateTime now = LocalDateTime.now();
        comment.setDate(now);
        assertEquals(now, comment.getDate());
    }

    @Test
    void equalsAndHashCodeTest() {
        Comment comment1 = new Comment(1);
        Comment comment2 = new Comment(1);
        Comment comment3 = new Comment(2);
        
        assertEquals(comment1, comment1);
        assertEquals(comment1, comment2);
        assertNotEquals(comment1, comment3);
        assertNotEquals(comment1, new Object());
        
        assertEquals(comment1.hashCode(), comment2.hashCode());
        assertNotEquals(comment1.hashCode(), comment3.hashCode());
    }
    
    @Test
    void toStringTest() {
        User user = new User();
        user.setNickname("testUser");
        Post post = new Post();
        post.setTitle("testPost");
        
        Comment comment = new Comment(1, user, post, "Hello");
        comment.setDate(LocalDateTime.of(2023, 1, 1, 12, 0));
        
        String expected = "{ id='1', user='testUser', post='testPost', text='Hello', date='2023-01-01T12:00'}";
        assertEquals(expected, comment.toString());
    }
}
