package com.arepacongofio.steamgram.entities;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void emptyConstructorTest() {
        Game game = new Game();
        assertNull(game.getId());
    }

    @Test
    void idConstructorTest() {
        Game game = new Game(1);
        assertEquals(1, game.getId());
    }

    @Test
    void igdbConstructorTest() {
        Developer dev = new Developer();
        Game game = new Game(100, "Title", "Banner", dev);
        
        assertEquals(100, game.getIdIgdb());
        assertEquals("Title", game.getTitle());
        assertEquals("Banner", game.getBanner());
        assertEquals(dev, game.getDeveloper());
    }

    @Test
    void basicConstructorTest() {
        Developer dev = new Developer();
        List<String> genres = new ArrayList<>();
        List<String> screenshots = new ArrayList<>();
        List<String> platforms = new ArrayList<>();
        
        Game game = new Game(100, "Title", "Desc", "Banner", dev, genres, screenshots, platforms);
        
        assertEquals(100, game.getIdIgdb());
        assertEquals("Title", game.getTitle());
        assertEquals("Desc", game.getDescription());
        assertEquals("Banner", game.getBanner());
        assertEquals(dev, game.getDeveloper());
        assertEquals(genres, game.getGenres());
        assertEquals(screenshots, game.getScreenshots());
        assertEquals(platforms, game.getPlatforms());
    }

    @Test
    void completeConstructorTest() {
        Developer dev = new Developer();
        Game game = new Game(1, 100, "Title", "Desc", "Banner", dev, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        
        assertEquals(1, game.getId());
        assertEquals(100, game.getIdIgdb());
    }

    @Test
    void gettersAndSettersTest() {
        Game game = new Game();
        game.setId(1);
        assertEquals(1, game.getId());
        
        game.setIdIgdb(100);
        assertEquals(100, game.getIdIgdb());
        
        game.setTitle("Title");
        assertEquals("Title", game.getTitle());
        
        game.setDescription("Desc");
        assertEquals("Desc", game.getDescription());
        
        game.setBanner("Banner");
        assertEquals("Banner", game.getBanner());
        
        Developer dev = new Developer();
        game.setDeveloper(dev);
        assertEquals(dev, game.getDeveloper());
        
        List<String> genres = new ArrayList<>();
        game.setGenres(genres);
        assertEquals(genres, game.getGenres());
        
        List<String> screenshots = new ArrayList<>();
        game.setScreenshots(screenshots);
        assertEquals(screenshots, game.getScreenshots());
        
        List<String> platforms = new ArrayList<>();
        game.setPlatforms(platforms);
        assertEquals(platforms, game.getPlatforms());
        
        List<Review> reviews = new ArrayList<>();
        game.setReviews(reviews);
        assertEquals(reviews, game.getReviews());
    }

    @Test
    void equalsAndHashCodeTest() {
        Game game1 = new Game(1);
        Game game2 = new Game(1);
        Game game3 = new Game(2);
        
        assertEquals(game1, game1);
        assertEquals(game1, game2);
        assertNotEquals(game1, game3);
        assertNotEquals(game1, new Object());
        
        assertEquals(game1.hashCode(), game2.hashCode());
        assertNotEquals(game1.hashCode(), game3.hashCode());
    }
}
