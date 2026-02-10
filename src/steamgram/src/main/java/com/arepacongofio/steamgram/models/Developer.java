package com.arepacongofio.steamgram.models;

import java.util.List;
import java.util.Objects;

public class Developer{
    Long id;
    List<User> users;
    String name;
    String password;
    List <Post> posts;
    List <User> followers;
    List <Game> developedGames;
    

    public Developer() {
    }

    public Developer(Long id) {
        this.id = id;
    }

    public Developer(List<User> users, String name, String password) {
        this.users = users;
        this.name = name;
        this.password = password;
    }

    public Developer(List<User> users, String name, String password, List<Post> posts, List<User> followers,
            List<Game> developedGames) {
        this.users = users;
        this.name = name;
        this.password = password;
        this.posts = posts;
        this.followers = followers;
        this.developedGames = developedGames;
    }

    public Developer(Long id, List<User> users, String name, String password, List<Post> posts, List<User> followers, List<Game> developedGames) {
        this.id = id;
        this.users = users;
        this.name = name;
        this.password = password;
        this.posts = posts;
        this.followers = followers;
        this.developedGames = developedGames;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<User> getFollowers() {
        return this.followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<Game> getDevelopedGames() {
        return this.developedGames;
    }

    public void setDevelopedGames(List<Game> developedGames) {
        this.developedGames = developedGames;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Developer)) {
            return false;
        }
        Developer developer = (Developer) o;
        return Objects.equals(id, developer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", users='" + getUsers() + "'" +
            ", name='" + getName() + "'" +
            ", password='" + getPassword() + "'" +
            ", posts='" + getPosts() + "'" +
            ", followers='" + getFollowers() + "'" +
            ", developedGames='" + getDevelopedGames() + "'" +
            "}";
    }
    
}
