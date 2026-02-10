package com.arepacongofio.steamgram.models;

import java.util.Date;
import java.util.Objects;

public class Like {
    Long id;
    Long idUser;
    Long idPost;
    Date date;

    public Like() {
    }

    public Like(Long id) {
        this.id = id;
    }

    public Like(Long idUser, Long idPost) {
        this.idUser = idUser;
        this.idPost = idPost;
    }

    public Like(Long id, Long idUser, Long idPost, Date date) {
        this.id = id;
        this.idUser = idUser;
        this.idPost = idPost;
        this.date = date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdPost() {
        return this.idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
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
