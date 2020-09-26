package com.example.flixster.models;

import org.json.JSONException;
import org.json.JSONObject;

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

}
