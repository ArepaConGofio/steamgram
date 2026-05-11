package com.arepacongofio.steamgram.entities;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DeveloperTest {

    @Test
    void emptyConstructorTest() {
        Developer developer = new Developer();
        assertNull(developer.getId());
    }

    @Test
    void idConstructorTest() {
        Developer developer = new Developer(1);
        assertEquals(1, developer.getId());
    }

    @Test
    void nameConstructorTest() {
        Developer developer = new Developer("DevName");
        assertEquals("DevName", developer.getName());
    }

    @Test
    void basicConstructorTest() {
        List<User> users = new ArrayList<>();
        Developer developer = new Developer(users, "DevName", "password");
        
        assertEquals(users, developer.getUsers());
        assertEquals("DevName", developer.getName());
    }

    @Test
    void constructorWithListsTest() {
        List<User> users = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        List<User> followers = new ArrayList<>();
        List<Game> games = new ArrayList<>();
        
        Developer developer = new Developer(users, "DevName", "pwd", posts, followers, games);
        
        assertEquals(users, developer.getUsers());
        assertEquals("DevName", developer.getName());
        assertEquals(posts, developer.getPosts());
        assertEquals(followers, developer.getFollowers());
        assertEquals(games, developer.getDevelopedGames());
    }

    @Test
    void completeConstructorTest() {
        List<User> users = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        List<User> followers = new ArrayList<>();
        List<Game> games = new ArrayList<>();
        
        Developer developer = new Developer(1, users, "DevName", "pwd", posts, followers, games);
        
        assertEquals(1, developer.getId());
        assertEquals("DevName", developer.getName());
    }

    @Test
    void gettersAndSettersTest() {
        Developer developer = new Developer();
        developer.setId(1);
        assertEquals(1, developer.getId());
        
        developer.setName("Name");
        assertEquals("Name", developer.getName());
        
        List<User> users = new ArrayList<>();
        developer.setUsers(users);
        assertEquals(users, developer.getUsers());
        
        List<Post> posts = new ArrayList<>();
        developer.setPosts(posts);
        assertEquals(posts, developer.getPosts());
        
        List<User> followers = new ArrayList<>();
        developer.setFollowers(followers);
        assertEquals(followers, developer.getFollowers());
        
        List<Game> games = new ArrayList<>();
        developer.setDevelopedGames(games);
        assertEquals(games, developer.getDevelopedGames());
    }

    @Test
    void equalsAndHashCodeTest() {
        Developer dev1 = new Developer(1);
        Developer dev2 = new Developer(1);
        Developer dev3 = new Developer(2);
        
        assertEquals(dev1, dev1);
        assertEquals(dev1, dev2);
        assertNotEquals(dev1, dev3);
        assertNotEquals(dev1, new Object());
        
        assertEquals(dev1.hashCode(), dev2.hashCode());
        assertNotEquals(dev1.hashCode(), dev3.hashCode());
    }
}
