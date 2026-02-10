package com.arepacongofio.steamgram.models;

import java.util.Objects;

public class Game {
    Long id;
    String title;
    String description;
    String banner;
    String publisher;
    Developer developer;
    String gender;

    public Game() {
    }

    public Game(Long id) {
        this.id = id;
    }

    public Game(String title, String description, String banner, String publisher, Developer developer, String gender) {
        this.title = title;
        this.description = description;
        this.banner = banner;
        this.publisher = publisher;
        this.developer = developer;
        this.gender = gender;
    }

    public Game(Long id, String title, String description, String banner, String publisher, Developer developer,
            String gender) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.banner = banner;
        this.publisher = publisher;
        this.developer = developer;
        this.gender = gender;
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

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
                ", gender='" + getGender() + "'" +
                "}";
    }

}
