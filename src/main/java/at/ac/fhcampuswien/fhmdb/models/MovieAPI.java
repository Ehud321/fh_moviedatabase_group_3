package at.ac.fhcampuswien.fhmdb.models;
//
//import com.google.gson.Gson;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//public class MovieAPI {
//    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";
//    private static final String USER_AGENT = "http.agent";
//    private final OkHttpClient client = new OkHttpClient();
//    private final Gson gson = new Gson();
//
//    public List<Movie> getAllMovies() throws IOException {
//        String url = BASE_URL;
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader(USER_AGENT, USER_AGENT)
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//            String json = response.body().string();
//            return gson.fromJson(json, new List<Movie>().getClass());
//        }
//    }
//
//    public List<Movie> searchMovies(String query, String genre) throws IOException {
//        String url = BASE_URL;
//        if (query != null && !query.isEmpty()) {
//            url += "?query=" + query;
//            if (genre != null && !genre.isEmpty()) {
//                url += "&genre=" + genre;
//            }
//        } else if (genre != null && !genre.isEmpty()) {
//            url += "?genre=" + genre;
//        }
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader(USER_AGENT, USER_AGENT)
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//            String json = response.body().string();
//            return gson.fromJson(json, new List<Movie>().getClass());
//        }
//    }
//
//    public static String getMostPopularActor(List<Movie> movies) {
//        return movies.stream()
//                .flatMap(movie -> movie.getMainCast().stream())
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet().stream()
//                .max(Map.Entry.comparingByValue())
//                .map(Map.Entry::getKey)
//                .orElse(null);
//    }
//
//    public static int getLongestMovieTitle(List<Movie> movies) {
//        return movies.stream()
//                .map(Movie::getTitle)
//                .mapToInt(String::length)
//                .max()
//                .orElse(0);
//    }
//
//    public static long countMoviesFrom(List<Movie> movies, String director) {
//        return movies.stream()
//                .filter(movie -> director.equals(movie.getDirector()))
//                .count();
//    }
//
//    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
//        return movies.stream()
//                .filter(movie -> movie.getYear() >= startYear && movie.getYear() <= endYear)
//                .collect(Collectors.toList());
//    }
//}

//
//import com.google.gson.Gson;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import java.io.IOException;
//import java.util.List;
//
//public class MovieAPI {
//
//    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";
//    private static final String USER_AGENT_HEADER = "User-Agent";
//    private static final String USER_AGENT_VALUE = "http.agent";
//
//    private OkHttpClient client;
//    private Gson gson;
//
//    public MovieAPI() {
//        this.client = new OkHttpClient();
//        this.gson = new Gson();
//    }
//
//    public List<Movie> getMovies(String query, String genre, Integer releaseYear, Double ratingFrom) throws IOException {
//        // Build the URL
//        String url = BASE_URL;
//        if (query != null || genre != null || releaseYear != null || ratingFrom != null) {
//            url += "?";
//            if (query != null) {
//                url += "query=" + query + "&";
//            }
//            if (genre != null) {
//                url += "genre=" + genre + "&";
//            }
//            if (releaseYear != null) {
//                url += "releaseYear=" + releaseYear + "&";
//            }
//            if (ratingFrom != null) {
//                url += "ratingFrom=" + ratingFrom + "&";
//            }
//            // Remove the trailing "&" from the URL
//            url = url.substring(0, url.length() - 1);
//        }
//
//
//        // Build the request with the User-Agent header
//        Request request = new Request.Builder()
//                .url(url)
//                .header(USER_AGENT_HEADER, USER_AGENT_VALUE)
//                .build();
//
//        // Send the request and parse the response
//        try (Response response = client.newCall(request).execute()) {
//            String json = response.body().string();
//            MovieResponse movieResponse = gson.fromJson(json, MovieResponse.class);
//            return movieResponse.getMovies();
//        }
//    }
//
//    private static class MovieResponse {
//        private List<Movie> movies;
//
//        public List<Movie> getMovies() {
//            return movies;
//        }
//    }
//}


import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieAPI {
    private static final String URL = "http://prog2.fh-campuswien.ac.at/movies";// API URL
    private static final String BASE_URL = "&";

    private static String buildUrl(String query, Genre genre, String releaseYear, String ratingFrom) {
        StringBuilder url = new StringBuilder(URL);
        if ((query != null && !query.isEmpty()) || genre != null || releaseYear != null || ratingFrom != null) {
            url.append("?");
            if (query != null && !query.isEmpty()) {
                url.append("query=").append(query).append(BASE_URL);
            }
            if (genre != null) {
                url.append("genre=").append(genre).append(BASE_URL);
            }
            if (releaseYear != null) {
                url.append("releaseYear=").append(releaseYear).append(BASE_URL);
            }
            if (ratingFrom != null) {
                url.append("ratingFrom=").append(ratingFrom).append(BASE_URL);
            }
        }
        return url.toString();
    }

    // create Movie Objects from http response (json) and return in Array
    public static List<Movie> getAllMovies() {
        return getAllMovies(null, null, null, null);
    }

    public static List<Movie> getAllMovies(String query, Genre genre, String releaseYear, String ratingFrom) {
        String url = buildUrl(query, genre, releaseYear, ratingFrom);
        Request request = new Request.Builder()
                .url(url)
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "http.agent")
                .build();

        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            Gson gson = new Gson();
            Movie[] movies = gson.fromJson(responseBody, Movie[].class);

            return Arrays.asList(movies);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<>();
    }
}
//
//    public String getMostPopularActor(List<Movie> movies) {
//        Map<String, Integer> mainCastMap = new HashMap<>();
//
//        movies.stream()
//                .filter(Objects::nonNull)
//                .flatMap(List::stream)
//                .map(Object::toString)
//                .forEach(actor -> {
//                    if (mainCastMap.containsKey(actor)) {
//                        mainCastMap.put(actor, mainCastMap.get(actor) + 1);
//                    } else {
//                        mainCastMap.put(actor, 1);
//                    }
//                });
//
//        return mainCastMap.entrySet()
//                .stream()
//                .max(Map.Entry.comparingByValue())
//                .map(Map.Entry::getKey)
//                .orElse(null);
//    }
//    public static int getLongestMovieTitle(List<Movie> movies) {
//        return movies.stream()
//                .map(Movie::getTitle)
//                .mapToInt(String::length)
//                .max()
//                .orElse(0);
//    }
//    public static long countMoviesFrom(List<Movie> movies, String director) {
//        return movies.stream()
//                .filter(movie -> director.equals(movie.getDirector()))
//                .count();
//    }
//    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
//        return movies.stream()
//                .filter(movie -> movie.getYear() >= startYear && movie.getYear() <= endYear)
//                .collect(Collectors.toList());
//    }
//}
