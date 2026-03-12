package com.arepacongofio.steamgram.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

/**
 * Class review
 */
@Entity
@Table(name = "review")
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    
    @JoinColumn(name = "id_user")
    Integer idUser;
    
    @JoinColumn(name = "id_game")
    Integer idGame;
    
    @Column(name = "title")
    String title;
    
    @Column(name = "description")
    String description;
    
    @Column(name = "rating")
    Integer rating;
    
    @Column(name = "create_date")
    LocalDateTime createDate;
    
    @Column(name = "update_date")
    LocalDateTime updateDate;

    /**
     * Empty constructor
     */
    public Review() {
    }

    /**
     * Constructor with only the id for search
     * @param id from review
     */
    public Review(Integer id) {
        this.id = id;
    }

    /**
     * Basic constructor 
     * @param idUser from review
     * @param idGame from review
     * @param title from review
     * @param description from review
     * @param rating from review
     */
    public Review(Integer idUser, Integer idGame, String title, String description, Integer rating) {
        this.idUser = idUser;
        this.idGame = idGame;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.createDate = LocalDateTime.now();
    }

    /**
     * Completed constructor
     * @param id from review
     * @param idUser from review
     * @param idGame from review
     * @param title from review
     * @param description from review
     * @param rating from review
     * @param createDate from review
     * @param updateDate from review
     */
    public Review(Integer id, Integer idUser, Integer idGame, String title, String description, Integer rating, LocalDateTime createDate,
            LocalDateTime updateDate) {
        this.id = id;
        this.idUser = idUser;
        this.idGame = idGame;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.createDate = createDate;
        this.updateDate = updateDate;
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

    public Integer getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdGame() {
        return this.idGame;
    }

    public void setIdGame(Integer idGame) {
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

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
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
