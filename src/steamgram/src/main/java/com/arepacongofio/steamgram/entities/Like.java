package com.arepacongofio.steamgram.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Class like
 */
@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "users")
    User user;

    @ManyToOne
    @JoinColumn(name = "id_post")
    Post post;

    @Column(name = "date")
    LocalDateTime date;

    /**
     * Empty constructor
     */
    public Like() {
    }

    /**
     * Constructor with only the id for search
     * 
     * @param id from Like
     */
    public Like(Integer id) {
        this.id = id;
    }

    /**
     * Basic constructor
     * 
     * @param user from Like
     * @param post from Like
     */
    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
        this.date = LocalDateTime.now();
    }

    /**
     * Complete constructor
     * 
     * @param id
     * @param user
     * @param post
     * @param date
     */
    public Like(Integer id, User user, Post post) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.date = LocalDateTime.now();
    }

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

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Like)) {
            return false;
        }
        Like like = (Like) o;
        return Objects.equals(id, like.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", idUser='" + getUser() + "'" +
                ", idPost='" + getPost() + "'" +
                ", date='" + getDate() + "'" +
                "}";
    }

}
