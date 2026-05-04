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
 * Class Comment
 */
@Entity
@Table(name = "comment")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "users")
    User user;

    @ManyToOne
    @JoinColumn(name = "post")
    Post post;
    
    @Column(name = "text")
    String text;

    @Column(name = "date")
    LocalDateTime date;

    /**
     * Empty constructor
     */
    public Comment() {
    }

    /**
     * Constructor with only the id for search 
     * @param id from Comment
     */
    public Comment(Integer id) {
        this.id = id;
    }

    /**
     * basic constructor
     * @param user from Comment
     * @param post from Comment
     * @param text from Comment
     */
    public Comment(User user, Post post, String text) {
        this.user = user;
        this.post = post;
        this.text = text;
        this.date = LocalDateTime.now();
    }

    /**
     * Complete constructor
     * @param id from Comment
     * @param user from Comment
     * @param post from Comment
     * @param text from Comment
     * @param date from Comment
     */
    public Comment(Integer id, User user, Post post, String text) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.text = text;
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

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
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
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser().getNickname() + "'" +
            ", post='" + getPost().getTitle() + "'" +
            ", text='" + getText() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
    
}
