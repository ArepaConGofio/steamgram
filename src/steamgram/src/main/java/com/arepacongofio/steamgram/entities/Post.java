package com.arepacongofio.steamgram.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Class post
 * 
 * @author strSalazar
 */
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "users")
    User user;

    @ManyToOne
    @JoinColumn(name = "game")
    Game game;

    @Column(name = "title")
    String title;

    @Column(name = "description", columnDefinition = "text")
    String description;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Like> likes;

    @OneToMany(mappedBy = "post")
    List<Comment> comments;

    @Column(name = "publicationDate")
    LocalDateTime publicationDate;

    /**
     * Empty constructor
     */
    public Post() {
    }

    /**
     * Constructor with only the id for shearch
     * 
     * @param id from Post
     */
    public Post(Integer id) {
        this.id = id;
    }

    /**
     * Basic constructor from Post
     * 
     * @param user      from Post
     * @param idGame      from Post
     * @param title       from Post
     * @param description from Post
     */
    public Post(User user, Game game, String title, String description) {
        this.user = user;
        this.game = game;
        this.title = title;
        this.description = description;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.publicationDate = LocalDateTime.now();
    }

    /**
     * Complete constructor
     * 
     * @param id              from Post
     * @param user          from Post
     * @param idGame          from Post
     * @param title           from Post
     * @param description     from Post
     * @param attachment      from Post
     * @param likes           from Post
     * @param comments        from Post
     * @param publicationDate from Post
     */
    public Post(Integer id, User user, Game game, String title, String description,
            List<Like> likes, List<Comment> comments) {
        this.id = id;
        this.user = user;
        this.game = game;
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.comments = comments;
        this.publicationDate = LocalDateTime.now();
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Like> getLikes() {
        return this.likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LocalDateTime getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Post)) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", user='" + getUser() + "'" +
                ", game='" + getGame() + "'" +
                ", title='" + getTitle() + "'" +
                ", description='" + getDescription() + "'" +
                ", likes='" + getLikes() + "'" +
                ", comments='" + getComments() + "'" +
                ", publicationDate='" + getPublicationDate() + "'" +
                "}";
    }

}
