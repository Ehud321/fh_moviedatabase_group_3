package at.ac.fhcampuswien.fhmdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@DatabaseTable(tableName = "watchlist")
public class WatchlistEntity {
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String apiId;

    @DatabaseField
    private String title;

    @DatabaseField
    private String description;

    @DatabaseField
    private String genres;

    @DatabaseField
    private int releaseYear;

    @DatabaseField
    private String imgUrl;

    @DatabaseField
    private int lengthInMinutes;

    @DatabaseField
    private String directors;

    @DatabaseField
    private String writers;

    @DatabaseField
    private String mainCast;

    @DatabaseField
    private double rating;

    public WatchlistEntity() {
        // ORMLite needs a no-arg constructor
    }

    public WatchlistEntity(Movie movie) {
        this.apiId = movie.getId();
        this.imgUrl = movie.getImgUrl();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.genres = listToString(movie.getGenres());
        this.releaseYear = movie.getReleaseYear();
        this.directors = listToString(movie.getDirectors());
        this.writers = listToString(movie.getWriters());
        this.mainCast = listToString(movie.getMainCast());
        this.lengthInMinutes = movie.getLengthInMinutes();
        this.rating = movie.getRating();
    }

    public String listToString(List<String> list) {
        return list.stream()
                .map(String::toString)
                .collect(Collectors.joining(","));
    }

    public List<String> stringToList( String str ) {
        return new ArrayList<>(Arrays.asList(str.split(",")));
    }

    public String getApiId() {
        return apiId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }

    public String getMainCast() {
        return mainCast;
    }

    public String getDirectors() {
        return directors;
    }

    public String getWriters() {
        return writers;
    }
}