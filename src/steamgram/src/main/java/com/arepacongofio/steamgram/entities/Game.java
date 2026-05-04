package com.arepacongofio.steamgram.entities;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Class Game
 */
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "id_igdb")
    Integer idIgdb;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "banner", nullable = true)
    String banner;

    @ManyToOne
    @JoinColumn(name = "id_developer")
    Developer developer;

    @ElementCollection
    @CollectionTable(name = "game_genres", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "genre")
    List<String> genres;

    @OneToMany(mappedBy = "game")
    List<Review> reviews;

    @ElementCollection
    @CollectionTable(name = "game_screenshots", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "screenshot")
    List<String> screenshots;

    @ElementCollection
    @CollectionTable(name = "game_plataforms", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "plataform")
    List<String> plataforms;

    /**
     * Empty constructor
     */
    public Game() {
    }

    /**
     * Constructor with only the id for search
     * 
     * @param id from Game
     */
    public Game(Integer id) {
        this.id = id;
    }

    /**
     * Basic constructor
     * 
     * @param title       from Game
     * @param description from Game
     * @param banner      from Game
     * @param publisher   from Game
     * @param developer   from Game
     * @param genre       from Game
     */
    public Game(Integer idIgdb, String title, String description, String banner, Developer developer,
            List<String> genre, List<String> screenshots, List<String> plataforms) {
        this.idIgdb = idIgdb;
        this.title = title;
        this.description = description;
        this.banner = banner;
        this.developer = developer;
        this.genres = genre;
        this.screenshots = screenshots;
        this.plataforms = plataforms;
    }

    /**
     * Complete constructor
     * 
     * @param id          from Game
     * @param title       from Game
     * @param description from Game
     * @param banner      from Game
     * @param publisher   from Game
     * @param developer   from Game
     * @param genre       from Game
     */
    public Game(Integer id, Integer idIgdb, String title, String description, String banner, Developer developer,
            List<String> genre, List<String> screenshots, List<String> plataforms) {
        this.id = id;
        this.idIgdb = idIgdb;
        this.title = title;
        this.description = description;
        this.banner = banner;
        this.developer = developer;
        this.genres = genre;
        this.screenshots = screenshots;
        this.plataforms = plataforms;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public Developer getDeveloper() {
        return this.developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public void setGenres(List<String> genre) {
        this.genres = genre;
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
                ", developer='" + getDeveloper() + "'" +
                ", genre='" + getGenres() + "'" +
                "}";
    }

}
