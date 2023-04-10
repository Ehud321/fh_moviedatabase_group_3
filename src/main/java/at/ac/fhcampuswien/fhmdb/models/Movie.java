package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List genres;
    private String director;
    private int year;

    // TODO add more properties here

    public Movie(String title, String description, List genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;

    }

    public Movie(String title, String description, List genres, String director, int year) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.director = director;
        this.year = year;
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



    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();

        // TODO add some dummy data here


        // Dummylist for movies
        movies.add(new Movie("Star Wars",
                "A young boy kills many people", List.of(Genre.ACTION, Genre.ADVENTURE)));
        movies.add(new Movie("Gone with the Wind",
                "1st world problems during the only real war on american soil.", List.of(Genre.ROMANCE, Genre.DRAMA, Genre.HORROR)));
        movies.add(new Movie("Untitled movie",
                "Test", List.of(Genre.DRAMA)));
        movies.add(new Movie("Titanic",
                "ship sink sad", List.of(Genre.DRAMA, Genre.ROMANCE, Genre.COMEDY)));
        movies.add(new Movie("Wild Wild West",
                "Will Smith fights robots in the Wild West", List.of(Genre.WESTERN, Genre.ADVENTURE, Genre.ACTION)));
        movies.add(new Movie("Pulp Fiction",
                "The lives of several criminals intertwine in a series of violent and unexpected events", List.of(Genre.CRIME, Genre.DRAMA)));
        movies.add(new Movie("Princess Mononoke",
                "A young warrior, Ashitaka, gets caught up in a struggle between the forest gods and a mining village", List.of(Genre.ANIMATION, Genre.ADVENTURE, Genre.FANTASY)));
        movies.add(new Movie("I Heart Huckabees",
                "A man hires existential detectives to help him uncover the meaning of his life", List.of(Genre.COMEDY, Genre.DRAMA)));

        return movies;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }


    public interface MyFunctionalInterface {
        void myMethod(String message);
    }


    public <R> R getMainCast() {
        return getMainCast();
    }
}
