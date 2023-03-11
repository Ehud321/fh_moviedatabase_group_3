package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.sun.net.httpserver.HttpContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    private static HomeController homeController;

    //Arrange?
    @BeforeAll
    public static void inti(){
       homeController = new HomeController();
    }

    @Test
    void show_initial_unfiltered_movies(){
        //Act?
        List<Movie> initialMovies = Movie.initializeMovies();
        //Assert?
        assertNotEquals(null, initialMovies);
    }

    @Test
    void

}