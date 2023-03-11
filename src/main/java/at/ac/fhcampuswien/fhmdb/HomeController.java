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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

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

        searchBtn.setOnAction(actionEvent -> {
            observableMovies.clear();
            observableMovies.addAll(allMovies);



            if(genreComboBox.getValue() != "no filter") {
                observableMovies.removeIf(n -> !(n.getGenres().contains(genreComboBox.getValue())));
                for (int i = 0; i < observableMovies.size(); i++) {
                    System.out.println("Genres of observableMovies" + observableMovies.get(i).getGenres());
                }
                for (int i = observableMovies.size(); i < allMovies.size(); i++) {
                    observableMovies.add(i, new Movie("empty", null, null));

                }
            }




        });

        // Sort button example:
        /*sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortBtn.setText("Sort (desc)");

                //add movietitles to ascList Array
                List <String> ascList = new ArrayList<>();
                for (int i = 0; i < observableMovies.size(); i++) {

                    if(observableMovies.get(i).getTitle() != "empty"){
                        ascList.add(observableMovies.get(i).getTitle());

                    }else {
                        System.out.println(ascList);
                    }
                }
                //sort the ascList Array
                Collections.sort(ascList);
                System.out.println("Sorted List: " + ascList);

                List <Movie> midMovieList = new ArrayList<>();

                for (int i = 0; i < ascList.size(); i++) {
                    if (observableMovies.get(i).getTitle().equals(ascList.get(i))) {
                        System.out.println("observableMovies: " + observableMovies.get(i).getTitle() + " == " + ascList.get(i));
                        midMovieList.add(observableMovies.get(i));
                        //observableMovies.remove(observableMovies.get(i));
                    } else {
                        System.out.println("observableMovies: " + observableMovies.get(i).getTitle() + " != " + ascList.get(i));

                        for (int j = i++; j < ascList.size() - i; j++) {

                            if (observableMovies.get(j).getTitle().equals(ascList.get(i))) {
                                midMovieList.add(observableMovies.get(j));
                                System.out.println("observableMovies: " + observableMovies.get(i).getTitle() + " == " + ascList.get(i));

                            } else if (observableMovies.get(j).getTitle().equals("empty")) {

                            }
                        }
                    }
                }
                for (int k = 0; k < midMovieList.size(); k++) {
                    System.out.println("midMovieList: " + midMovieList.get(k).getTitle() + " ");
                    //observableMovies.remove(observableMovies.get(i));

                }

            } else {
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });*/

          sortBtn.setOnAction(actionEvent -> {
              int sortClicks = 0;
              if (sortBtn.getText().equals("Sort (desc)") && sortClicks % 2 == 0) {
                  observableMovies.sort(Comparator.comparing(Movie::getTitle));
                  //sortState = SortState.ASCENDING;
                  sortBtn.setText("Sort (asc)");
              }else{
                  observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
                  //sortState = SortState.DESCENDING;
                  sortBtn.setText("Sort (desc)");
              }


          });

          /*searchBtn.setOnAction(actionEvent -> {
              List<Movie> filteredMovies = new ArrayList<>();

              for (Movie movie : observableMovies) {
                  if ((searchField.toString() == null || movie.getTitle().toLowerCase().contains(searchField.toString().toLowerCase())
                          || movie.getDescription().toLowerCase().contains(searchField.toString().toLowerCase()))) {
                      filteredMovies.add(movie);
                  }
              }
              observableMovies.addAll(filteredMovies);
          });*/

    }

}