package com.arepacongofio.steamgram.models;

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
    @JoinColumn(name = "id_user")
    Integer idUser;

    @ManyToOne
    @JoinColumn(name = "id_post")
    Integer idPost;
    
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
     * @param idUser from Comment
     * @param idPost from Comment
     * @param text from Comment
     */
    public Comment(Integer idUser, Integer idPost, String text) {
        this.idUser = idUser;
        this.idPost = idPost;
        this.text = text;
    }

    /**
     * Complete constructor
     * @param id from Comment
     * @param idUser from Comment
     * @param idPost from Comment
     * @param text from Comment
     * @param date from Comment
     */
    public Comment(Integer id, Integer idUser, Integer idPost, String text, LocalDateTime date) {
        this.id = id;
        this.idUser = idUser;
        this.idPost = idPost;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdPost() {
        return this.idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
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
            ", idUser='" + getIdUser() + "'" +
            ", idPost='" + getIdPost() + "'" +
            ", text='" + getText() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
    
}
