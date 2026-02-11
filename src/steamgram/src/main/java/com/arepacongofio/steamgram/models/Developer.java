package com.arepacongofio.steamgram.models;

import java.util.List;
import java.util.Objects;

/**
 * class Developer
 */
public class Developer{
    Integer id;
    List<User> users;
    String name;
    String password;
    List <Post> posts;
    List <User> followers;
    List <Game> developedGames;
    
    /**
     * Empty constructor 
     */
    public Developer() {
    }

    /**
     * Constructor only with the id for search
     * @param id from Developer
     */
    public Developer(Integer id) {
        this.id = id;
    }

    /**
     * Basic constructor
     * @param users from Developer
     * @param name from Developer
     * @param password from Developer
     */
    public Developer(List<User> users, String name, String password) {
        this.users = users;
        this.name = name;
        this.password = password;
    }

    /**
     * Constructor with posts,followers and delveloped games
     * @param users from Developer
     * @param name from Developer
     * @param password from Developer
     * @param posts from Developer
     * @param followers from Developer
     * @param developedGames from Developer
     */
    public Developer(List<User> users, String name, String password, List<Post> posts, List<User> followers,
            List<Game> developedGames) {
        this.users = users;
        this.name = name;
        this.password = password;
        this.posts = posts;
        this.followers = followers;
        this.developedGames = developedGames;
    }

    /**
     * Complete constructor from Developer
     * @param id from Developer
     * @param users from Developer
     * @param name from Developer
     * @param password from Developer
     * @param posts from Developer
     * @param followers from Developer
     * @param developedGames from Developer
     */
    public Developer(Integer id, List<User> users, String name, String password, List<Post> posts, List<User> followers, List<Game> developedGames) {
        this.id = id;
        this.users = users;
        this.name = name;
        this.password = password;
        this.posts = posts;
        this.followers = followers;
        this.developedGames = developedGames;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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
