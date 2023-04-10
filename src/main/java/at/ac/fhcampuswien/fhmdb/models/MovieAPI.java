package at.ac.fhcampuswien.fhmdb.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MovieAPI {

    public String getMostPopularActor(List<Movie> movies) {
        Map<String, Integer> mainCastMap = new HashMap<>();
        movies.stream().filter(Objects::nonNull).map(Movie::getMainCast).flatMap(List::stream)
                .forEach(i -> {
                    if (mainCastMap.containsKey(i)) {
                        mainCastMap.put(i, mainCastMap.get(i)+1);
                    }else {
                        mainCastMap.put(i, 1);
                    }
                });
        return mainCastMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    public int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream().filter(Objects::nonNull).map(Movie::getTitle).map(String::length).max(Integer::compareTo).get();
    }

    public long countMoviesFrom(List<Movie> movies, String director){
        return movies.stream().filter(Objects::nonNull).filter(movie -> movie.getDirectors().contains(director)).count();
    }

    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream().filter(Objects::nonNull).filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }
}
