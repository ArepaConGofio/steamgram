package com.arepacongofio.steamgram.models;

import java.util.List;
import java.util.Objects;

public class Game {
    Long id;
    String title;
    String description;
    String banner;
    String publisher;
    Developer developer;
    String genre;
    
    List<Review> reviews;

    public Game() {
    }

    public Game(Long id) {
        this.id = id;
    }

    public Game(String title, String description, String banner, String publisher, Developer developer, String genre) {
        this.title = title;
        this.description = description;
        this.banner = banner;
        this.publisher = publisher;
        this.developer = developer;
        this.genre = genre;
    }

    public Game(Long id, String title, String description, String banner, String publisher, Developer developer,
            String genre) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.banner = banner;
        this.publisher = publisher;
        this.developer = developer;
        this.genre = genre;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBanner() {
        return this.banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Developer getDeveloper() {
        return this.developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                ", description='" + getDescription() + "'" +
                ", banner='" + getBanner() + "'" +
                ", publisher='" + getPublisher() + "'" +
                ", developer='" + getDeveloper() + "'" +
                ", genre='" + getGenre() + "'" +
                "}";
    }

}
