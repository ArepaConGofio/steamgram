package com.arepacongofio.steamgram.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Class User
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "user")
    String user;

    @Column(name = "password")
    String password;

    @Column(name = "games")
    @OneToMany(mappedBy = "User")
    List<Game> games;

    @Column(name = "posts")
    @OneToMany(mappedBy = "User")
    List<Post> posts;

    @Column(name = "follows")
    @OneToMany(mappedBy = "User")
    List<User> follows;

    @Column(name = "followers")
    @OneToMany(mappedBy = "User")
    List<User> followers;

    @OneToMany(mappedBy = "User")
    List<Review> reviews;

    /**
     * Empty constructor
     */
    public User() {
    }

    /**
     * Constructor with only id for search
     * @param id for user
     */
    public User(Long id) {
        this.id = id;
    }

    /**
     * Basic User constructor
     * @param name from user
     * @param user from user
     * @param password from user
     */
    public User(String name, String user, String password) {
        this.name = name;
        this.user = user;
        this.password = password;
        this.followers = new ArrayList<>();
        this.follows = new ArrayList<>();
        this.games = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    /**
     * Constructor with out id
     * @param name from user
     * @param user from user
     * @param games from user
     * @param posts from user
     * @param follows from user
     * @param followers from user
     */
    public User(String name, String user, List<Game> games, List<Post> posts, List<User> follows,
            List<User> followers) {
        this.name = name;
        this.user = user;
        this.games = games;
        this.posts = posts;
        this.follows = follows;
        this.followers = followers;
    }

    /**
     * complete constructor
     * @param id from user
     * @param name from user
     * @param user from user
     * @param password from user
     * @param games from user
     * @param posts from user
     * @param follows from user
     * @param followers from user
     */
    public User(Long id, String name, String user, String password, List<Game> games, List<Post> posts,
            List<User> follows, List<User> followers) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
        this.games = games;
        this.posts = posts;
        this.follows = follows;
        this.followers = followers;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<User> getFollows() {
        return this.follows;
    }

    public void setFollows(List<User> follows) {
        this.follows = follows;
    }

    public List<User> getFollowers() {
        return this.followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", user='" + getUser() + "'" +
                ", games='" + getGames() + "'" +
                ", posts='" + getPosts() + "'" +
                ", follows='" + getFollows() + "'" +
                ", followers='" + getFollowers() + "'" +
                "}";
    }

}
