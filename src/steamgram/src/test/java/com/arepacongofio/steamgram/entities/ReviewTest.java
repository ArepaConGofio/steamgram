package com.arepacongofio.steamgram.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    @Test
    void emptyConstructorTest() {
        Review review = new Review();
        assertNull(review.getId());
    }

    @Test
    void idConstructorTest() {
        Review review = new Review(1);
        assertEquals(1, review.getId());
    }

    @Test
    void basicConstructorTest() {
        User user = new User();
        Game game = new Game();
        
        Review review = new Review(user, game, "Title", "Desc", 5);
        
        assertEquals(user, review.getUser());
        assertEquals(game, review.getGame());
        assertEquals("Title", review.getTitle());
        assertEquals("Desc", review.getDescription());
        assertEquals(5, review.getRating());
        assertNotNull(review.getCreateDate());
    }

    @Test
    void updateConstructorTest() {
        User user = new User();
        Game game = new Game();
        LocalDateTime updateDate = LocalDateTime.now();
        
        Review review = new Review(1, user, game, "Title", "Desc", 5, updateDate);
        
        assertEquals(1, review.getId());
        assertEquals(user, review.getUser());
        assertEquals(game, review.getGame());
        assertEquals("Title", review.getTitle());
        assertEquals("Desc", review.getDescription());
        assertEquals(5, review.getRating());
        assertNotNull(review.getUpdateDate());
    }

    @Test
    void gettersAndSettersTest() {
        Review review = new Review();
        review.setId(1);
        assertEquals(1, review.getId());
        
        User user = new User();
        review.setUser(user);
        assertEquals(user, review.getUser());
        
        Game game = new Game();
        review.setGame(game);
        assertEquals(game, review.getGame());
        
        review.setTitle("Title");
        assertEquals("Title", review.getTitle());
        
        review.setDescription("Desc");
        assertEquals("Desc", review.getDescription());
        
        review.setRating(5);
        assertEquals(5, review.getRating());
        
        LocalDateTime now = LocalDateTime.now();
        review.setCreateDate(now);
        assertEquals(now, review.getCreateDate());
        
        review.setUpdateDate(now);
        assertEquals(now, review.getUpdateDate());
    }

    @Test
    void equalsAndHashCodeTest() {
        Review review1 = new Review(1);
        Review review2 = new Review(1);
        Review review3 = new Review(2);
        
        assertEquals(review1, review1);
        assertEquals(review1, review2);
        assertNotEquals(review1, review3);
        assertNotEquals(review1, new Object());
        
        assertEquals(review1.hashCode(), review2.hashCode());
        assertNotEquals(review1.hashCode(), review3.hashCode());
    }
}
