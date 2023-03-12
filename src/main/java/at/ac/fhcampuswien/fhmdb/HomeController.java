package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortState;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public SortState sortState = SortState.NONE;

    public List<Movie> allMovies = Movie.initializeMovies();

    public final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

      @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          observableMovies.addAll(allMovies);         // add dummy data to observable list

          // initialize UI stuff
          movieListView.setItems(observableMovies);   // set data of observable list to list view
          movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data


          // TODO add genre filter items with genreComboBox.getItems().addAll(...)
          genreComboBox.setPromptText("Filter by Genre");
          genreComboBox.getItems().add("no filter");
          genreComboBox.getItems().addAll(Genre.values());
          // TODO add event handlers to buttons and call the regarding methods
          // either set event handlers in the fxml file (onAction) or add them here


        sortBtn.setOnAction(actionEvent -> {
            sortMovies();
        });

        searchBtn.setOnAction(actionEvent -> {
            searchMovies();
        });
    }

    public void initializeState() {
        observableMovies.clear();
        observableMovies.addAll(allMovies);

    }

    public void sortMovies() {
        int sortClicks = 0;
        if (sortBtn.getText().equals("Sort (desc)") && sortClicks % 2 == 0) {
            observableMovies.sort(Comparator.comparing(Movie::getTitle));
            sortState = SortState.ASCENDING;
            sortBtn.setText("Sort (asc)");
        } else {
            observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
            sortState = SortState.DESCENDING;
            sortBtn.setText("Sort (desc)");
        }
    }
    public void searchMovies() {
        List<Movie> filteredMovies = new ArrayList<>();
        if (genreComboBox.getValue() == null || genreComboBox.getValue() == "no filter") {
            observableMovies.clear();
            observableMovies.addAll(allMovies);
        } else if (genreComboBox.getValue() != "no filter") {
            observableMovies.clear();
            observableMovies.addAll(allMovies);
            observableMovies.removeIf(n -> !(n.getGenres().contains(genreComboBox.getValue())));
        }

              if(searchField.getText() != null) {
                  for (Movie movie : observableMovies) {
                      if (movie.getTitle().toLowerCase().contains(searchField.getText().toLowerCase()) || movie.getDescription().toLowerCase().contains(searchField.getText().toLowerCase()) || movie.getGenres().toString().toLowerCase().contains(searchField.getText().toLowerCase())) {
                              filteredMovies.add(movie);
                          }
                      }
                      observableMovies.clear();
                      observableMovies.addAll(filteredMovies);
                  }


    }

}