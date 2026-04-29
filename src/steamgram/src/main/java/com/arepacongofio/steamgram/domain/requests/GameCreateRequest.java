package com.arepacongofio.steamgram.domain.requests;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class GameCreateRequest {
    @NotBlank
    String title;

    String description;
    String banner;

    @NotBlank
    String publisher;

    @NotBlank
    Integer developerId;

    List<String> genres;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genre) {
        this.genres = genre;
    }

}
