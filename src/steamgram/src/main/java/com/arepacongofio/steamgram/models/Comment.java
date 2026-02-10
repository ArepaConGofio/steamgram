package com.arepacongofio.steamgram.models;

import java.util.Date;
import java.util.Objects;

public class Comment {
    Long id;
    Long idUser;
    Long idPost;
    String text;
    Date date;

    public Comment() {
    }

    public Comment(Long id) {
        this.id = id;
    }

    public Comment(Long idUser, Long idPost, String text) {
        this.idUser = idUser;
        this.idPost = idPost;
        this.text = text;
    }

    public Comment(Long id, Long idUser, Long idPost, String text, Date date) {
        this.id = id;
        this.idUser = idUser;
        this.idPost = idPost;
        this.text = text;
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

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
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
