package com.arepacongofio.steamgram.models;

import java.util.Date;
import java.util.Objects;

public class Review {
    Long id;
    Long idUser;
    Long idGame;
    String title;
    String description;
    Integer rating;
    Date createDate;
    Date updateDate;

    public Review() {
    }

    public Review(Long id) {
        this.id = id;
    }

    public Review(Long idUser, Long idGame, String title, String description, Integer rating) {
        this.idUser = idUser;
        this.idGame = idGame;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public Review(Long id, Long idUser, Long idGame, String title, String description, Integer rating, Date createDate,
            Date updateDate) {
        this.id = id;
        this.idUser = idUser;
        this.idGame = idGame;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.createDate = createDate;
        this.updateDate = updateDate;
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

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Review)) {
            return false;
        }
        Review review = (Review) o;
        return Objects.equals(id, review.id);
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
                ", rating='" + getRating() + "'" +
                ", createDate='" + getCreateDate() + "'" +
                ", updateDate='" + getUpdateDate() + "'" +
                "}";
    }

}
