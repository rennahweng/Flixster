package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

// This class encapsulate the idea of an Movie
// A model class to use as the data source of movies
// Make movie parcelable
@Parcel
public class Movie {

    int movieId;
    String title;
    String overview;
    String posterPath;
    String backdropPath;
    double rating;
    String releaseDate;

    // Empty constructor needed by Parcel library
    public Movie() {
    }


    // Constructor
    public Movie(JSONObject jsonObject) throws JSONException {
        // Read and parse the fields we care about from json response body
        movieId = jsonObject.getInt("id");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        rating = jsonObject.getDouble("vote_average");
        releaseDate = jsonObject.getString("release_date");
    }

    // Return a list of movies from the jsonArray "results"
    public static List<Movie> moviesFromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movieList.add( new Movie( movieJsonArray.getJSONObject(i) ) );
        }
        return movieList;
    }

    public int getMovieId() {
        return movieId;
    }


    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        // hardcode size to be a width of 342
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
        /*
         * Proper way:
         * 1. Fetch all available sizes
         * 2. Append it to the base URL: https://image.tmdb.org/t/p/
         * 3. Then add in the relative path from posterPath in "results"
         */
    }

    public String getBackdropPath() {
        // hardcode size to be a width of 342
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public double getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

}
