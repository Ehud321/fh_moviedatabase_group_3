package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List genres;

    // TODO add more properties here

    public Movie(String title, String description, List genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List getGenres() {
        return genres;
    }

    public Movie getEmptyMovie() {

        Movie emptyMovie = new Movie(null, null, null);
        return emptyMovie;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();

        // TODO add some dummy data here


        // Dummylist for movies
        movies.add(new Movie("Star Wars", "A young boy kills many people", List.of(Genres.ACTION, Genres.ADVENTURE)));
        movies.add(new Movie("Gone with the Wind", "1st world problems during the only real war on american soil.", List.of(Genres.ROMANCE, Genres.DRAMA, Genres.HORROR)));
        movies.add(new Movie("Untitled movie", "Test", List.of(Genres.DRAMA)));
        movies.add(new Movie("Titanic", "ship sink sad", List.of(Genres.DRAMA, Genres.ROMANCE, Genres.COMEDY)));
        movies.add(new Movie("Wild Wild West", "Will Smith fights robots in the Wild West", List.of(Genres.WESTERN, Genres.ADVENTURE, Genres.ACTION)));

        return movies;
    }


}
