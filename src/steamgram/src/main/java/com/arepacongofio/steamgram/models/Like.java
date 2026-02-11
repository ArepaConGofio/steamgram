package com.arepacongofio.steamgram.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Like {
    Integer id;
    Integer idUser;
    Integer idPost;
    LocalDateTime date;

    /**
     * Empty constructor
     */
    public Like() {
    }

    /**
     * Constructor with only the id for search
     * @param id from Like
     */
    public Like(Integer id) {
        this.id = id;
    }

    /**
     * Basic constructor
     * @param idUser from Like
     * @param idPost from Like
     */
    public Like(Integer idUser, Integer idPost) {
        this.idUser = idUser;
        this.idPost = idPost;
        this.date = LocalDateTime.now();
    }

    /**
     * 
     * @param id
     * @param idUser
     * @param idPost
     * @param date
     */
    public Like(Integer id, Integer idUser, Integer idPost, LocalDateTime date) {
        this.id = id;
        this.idUser = idUser;
        this.idPost = idPost;
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
                ", idUser='" + getIdUser() + "'" +
                ", idPost='" + getIdPost() + "'" +
                ", date='" + getDate() + "'" +
                "}";
    }

}
