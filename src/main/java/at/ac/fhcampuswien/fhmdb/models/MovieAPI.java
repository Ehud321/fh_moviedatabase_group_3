package at.ac.fhcampuswien.fhmdb.models;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MovieAPI {
    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";
    private static final String USER_AGENT = "http.agent";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public List getAllMovies() throws IOException {
        String url = BASE_URL;
        Request request = new Request.Builder()
                .url(url)
                .addHeader(USER_AGENT, USER_AGENT)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return gson.fromJson(json, List.class);
        }
    }

    public List searchMovies(String query, String genre) throws IOException {
        String url = BASE_URL;
        if (query != null && !query.isEmpty()) {
            url += "?query=" + query;
            if (genre != null && !genre.isEmpty()) {
                url += "&genre=" + genre;
            }
        } else if (genre != null && !genre.isEmpty()) {
            url += "?genre=" + genre;
        }
        Request request = new Request.Builder()
                .url(url)
                .addHeader(USER_AGENT, USER_AGENT)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return gson.fromJson(json, List.class);
        }
    }

    public String getMostPopularActor(List<Movie> movies) {
        Map<String, Integer> mainCastMap = new HashMap<>();

        movies.stream()
                .filter(Objects::nonNull)
                .map(Movie::getMainCast)
                .flatMap(List::stream)
                .map(Object::toString)
                .forEach(actor -> {
                    if (mainCastMap.containsKey(actor)) {
                        mainCastMap.put(actor, mainCastMap.get(actor) + 1);
                    } else {
                        mainCastMap.put(actor, 1);
                    }
                });

        return mainCastMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    public static int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .map(Movie::getTitle)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }
    public static long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> director.equals(movie.getDirector()))
                .count();
    }
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getYear() >= startYear && movie.getYear() <= endYear)
                .collect(Collectors.toList());
    }
}
