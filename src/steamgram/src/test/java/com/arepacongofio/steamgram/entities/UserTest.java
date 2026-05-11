package com.arepacongofio.steamgram.entities;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void emptyConstructorTest() {
        User user = new User();
        assertNull(user.getId());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void idConstructorTest() {
        User user = new User(1);
        assertEquals(1, user.getId());
    }

    @Test
    void basicConstructorTest() {
        User user = new User("Name", "Nickname", "password");
        
        assertEquals("Name", user.getName());
        assertEquals("Nickname", user.getNickname());
        assertEquals("password", user.getPassword());
        assertNotNull(user.getGames());
        assertNotNull(user.getPosts());
    }

    @Test
    void constructorWithListsTest() {
        List<Game> games = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        
        User user = new User("Name", "Nickname", games, posts);
        
        assertEquals("Name", user.getName());
        assertEquals("Nickname", user.getNickname());
        assertEquals(games, user.getGames());
        assertEquals(posts, user.getPosts());
    }

    @Test
    void completeConstructorTest() {
        List<Game> games = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        
        User user = new User(1, "Name", "Nickname", "password", games, posts);
        
        assertEquals(1, user.getId());
        assertEquals("Name", user.getName());
        assertEquals("Nickname", user.getNickname());
        assertEquals("password", user.getPassword());
        assertEquals(games, user.getGames());
        assertEquals(posts, user.getPosts());
    }

    @Test
    void gettersAndSettersTest() {
        User user = new User();
        user.setId(1);
        assertEquals(1, user.getId());
        
        user.setName("Name");
        assertEquals("Name", user.getName());
        
        user.setNickname("Nickname");
        assertEquals("Nickname", user.getNickname());
        
        user.setEmail("email@test.com");
        assertEquals("email@test.com", user.getEmail());
        
        user.setPassword("password");
        assertEquals("password", user.getPassword());
        
        user.setRole(Role.ADMIN);
        assertEquals(Role.ADMIN, user.getRole());
        
        user.setAvatarUrl("url");
        assertEquals("url", user.getAvatarUrl());
        
        List<Game> games = new ArrayList<>();
        user.setGames(games);
        assertEquals(games, user.getGames());
        
        List<Post> posts = new ArrayList<>();
        user.setPosts(posts);
        assertEquals(posts, user.getPosts());
        
        List<Review> reviews = new ArrayList<>();
        user.setReviews(reviews);
        assertEquals(reviews, user.getReviews());
    }

    @Test
    void equalsAndHashCodeTest() {
        User user1 = new User(1);
        User user2 = new User(1);
        User user3 = new User(2);
        
        assertEquals(user1, user1);
        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertNotEquals(user1, new Object());
        
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }
}
