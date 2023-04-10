package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HomeControllerTest {

    private static HomeController homeController;

    //Arrange?
    @BeforeAll
    public static void init() {
        homeController = new HomeController();
    }

    public static void setHomeController(HomeController homeController) {
        HomeControllerTest.homeController = homeController;
    }

    @Test
    void show_initial_unfiltered_movies_not_null() {
        //Act?
        List<Movie> initialMovies = Movie.initializeMovies();
        //Assert?
        assertNotEquals(null, initialMovies);
    }

    @Test
    void get_title_test() {
        Movie movie = new Movie("Star Wars", "A young boy kills many people", List.of(Genre.ACTION, Genre.ADVENTURE));
        assertEquals(movie.getTitle(), "Star Wars");
    }

    @Test
    void get_description_test() {
        Movie movie = new Movie("Gone with the Wind",
                "1st world problems during the only real war on american soil.", List.of(Genre.ROMANCE, Genre.DRAMA, Genre.HORROR));
        assertEquals(movie.getDescription(), "1st world problems during the only real war on american soil.");
    }

    @Test
    void get_genres_test() {
        Movie movie = new Movie("Pulp Fiction",
                "The lives of several criminals intertwine in a series of violent and unexpected events", List.of(Genre.CRIME, Genre.DRAMA));
        assertEquals(movie.getGenres(), List.of(Genre.CRIME, Genre.DRAMA));
    }

    @Test
    void movies_and_oberservableMovies_are_equal() {
        homeController.initializeState();
        assertEquals(homeController.allMovies, homeController.observableMovies);
        // possibly test for same length of the lists
    }

    @Test
    void sort_btn_test() {
        init();
        homeController.initializeState();
        homeController.sortMovies();
        assertEquals(homeController.allMovies.get(0), homeController.observableMovies.get(0));
    }

    @Test
    void genreBoxTest() {
        init();
        homeController.initializeState();
        homeController.genreComboBox.setValue(Genre.ACTION);
        homeController.searchMovies();
        System.out.println(homeController.observableMovies.get(0));
        System.out.println(homeController.allMovies.get(0));
    }
}