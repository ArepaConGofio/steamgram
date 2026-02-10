package com.arepacongofio.steamgram.models;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Post {
    Long id;
    Long idUser;
    Long idGame;
    String title;
    String description;
    String attachment;
    List<Like> likes;
    List<Comment> comments;
    Date publicationDate;

    public Post() {
    }

    public Post(Long id) {
        this.id = id;
    }

    public Post(Long idUser, Long idGame, String title, String description, String attachment) {
        this.idUser = idUser;
        this.idGame = idGame;
        this.title = title;
        this.description = description;
        this.attachment = attachment;
    }

    public Post(Long id, Long idUser, Long idGame, String title, String description, String attachment,
            List<Like> likes, List<Comment> comments, Date publicationDate) {
        this.id = id;
        this.idUser = idUser;
        this.idGame = idGame;
        this.title = title;
        this.description = description;
        this.attachment = attachment;
        this.likes = likes;
        this.comments = comments;
        this.publicationDate = publicationDate;
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

    public Long getIdGame() {
        return this.idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
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

    public String getAttachment() {
        return this.attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
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

    public Date getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
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
                ", idUser='" + getIdUser() + "'" +
                ", idGame='" + getIdGame() + "'" +
                ", title='" + getTitle() + "'" +
                ", description='" + getDescription() + "'" +
                ", attachment='" + getAttachment() + "'" +
                ", likes='" + getLikes() + "'" +
                ", comments='" + getComments() + "'" +
                ", publicationDate='" + getPublicationDate() + "'" +
                "}";
    }

}
