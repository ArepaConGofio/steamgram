package com.arepacongofio.steamgram.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

/**
 * Class User
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "nickname", unique = true)
    String nickname;

    @Column(name = "email", unique = true)
    @Email
    String email;

    @Column(name = "password")
    String password;

    @ManyToMany
    @JoinTable(
        name = "user_games",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    List<Game> games;

    @OneToMany(mappedBy = "user")
    List<Post> posts;

    @ManyToMany
    @JoinTable(
        name = "user_follows",
        joinColumns = @JoinColumn(name = "follower_id"),
        inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    List<User> follows;

    @ManyToMany(mappedBy = "follows")
    List<User> followers;

    @OneToMany(mappedBy = "user")
    List<Review> reviews;

    /**
     * Empty constructor
     */
    public User() {
    }

    /**
     * Constructor with only the id for search
     * 
     * @param id for user
     */
    public User(Integer id) {
        this.id = id;
    }

    /**
     * Basic User constructor
     * 
     * @param name     from user
     * @param user     from user
     * @param password from user
     */
    public User(String name, String user, String password) {
        this.name = name;
        this.nickname = user;
        this.password = password;
        this.followers = new ArrayList<>();
        this.follows = new ArrayList<>();
        this.games = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    /**
     * Constructor with out id
     * 
     * @param name      from user
     * @param user      from user
     * @param games     from user
     * @param posts     from user
     * @param follows   from user
     * @param followers from user
     */
    public User(String name, String user, List<Game> games, List<Post> posts, List<User> follows,
            List<User> followers) {
        this.name = name;
        this.nickname = user;
        this.games = games;
        this.posts = posts;
        this.follows = follows;
        this.followers = followers;
    }

    /**
     * complete constructor
     * 
     * @param id        from user
     * @param name      from user
     * @param user      from user
     * @param password  from user
     * @param games     from user
     * @param posts     from user
     * @param follows   from user
     * @param followers from user
     */
    public User(Integer id, String name, String user, String password, List<Game> games, List<Post> posts,
            List<User> follows, List<User> followers) {
        this.id = id;
        this.name = name;
        this.nickname = user;
        this.password = password;
        this.games = games;
        this.posts = posts;
        this.follows = follows;
        this.followers = followers;
    }

    /**
     * Getters and Setters
     */

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String user) {
        this.nickname = user;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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
                ", user='" + getNickname() + "'" +
                ", games='" + getGames() + "'" +
                ", posts='" + getPosts() + "'" +
                ", follows='" + getFollows() + "'" +
                ", followers='" + getFollowers() + "'" +
                "}";
    }

}
