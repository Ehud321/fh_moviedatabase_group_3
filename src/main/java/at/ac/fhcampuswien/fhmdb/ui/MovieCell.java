package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.sun.javafx.css.StyleClassSet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genres = new Label();
    private final Label rating = new Label();
    private final Label releaseYear = new Label();
    private final VBox layout = new VBox(title, detail, genres, releaseYear, rating);
    private StyleClassSet genre;


    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);

        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );
            genres.setText(movie.getGenres() != null
                    ? movie.getGenres().toString().replace("[", "").replace("]", "")
                    : "No genres available"
            );

//            String genre = movie.getGenres()
//                    .stream()
//                    .map(Enum::toString)
//                    .collect(Collectors.joining(", "));
//            genre.setText(genre);

            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));
            releaseYear.setText("Released: " + String.valueOf(movie.getReleaseYear()));
            releaseYear.getStyleClass().add("text-white");
            rating.setText("Rating: " + String.valueOf(movie.getRating()));
            rating.getStyleClass().add("text-white");

            // layout
            title.getFont();
            title.fontProperty().set(Font.font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            genres.setMaxWidth(this.getScene().getWidth() - 30);
            genres.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(Pos.CENTER_LEFT);
            setGraphic(layout);
        }
    }
}

