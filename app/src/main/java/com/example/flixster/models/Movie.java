package com.example.flixster.models;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// This class encapsulate the idea of an Movie
public class Movie {
    String posterPath;
    String title;
    String overview;

    // Constructor
    public Movie(JSONObject jsonObject) throws JSONException {
        // Read and parse the fields we care about from json response body
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    // Return a list of movies from the jsonArray "results"
    public static List<Movie> moviesFromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movieList.add( new Movie( movieJsonArray.getJSONObject(i) ) );
        }
        return movieList;
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

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
