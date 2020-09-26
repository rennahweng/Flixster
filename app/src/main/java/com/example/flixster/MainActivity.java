package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "MainActivity";

    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movieList = new ArrayList<Movie>();

        // Create an adapter
        final MovieAdapter movieAdapter = new MovieAdapter(this, movieList);

        // Set adapter on RecycleView
        rvMovies.setAdapter(movieAdapter);

        // Set a Layout Manager on RecycleView
        rvMovies.setLayoutManager(new LinearLayoutManager(this));


        // Asynchronous HTTP request
        AsyncHttpClient client = new AsyncHttpClient();
        // GET request on an url
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                // from debugger, look at the json response and parse it
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG, "Results: " + results.toString());
                    movieList.addAll( Movie.moviesFromJsonArray(results) );
                    // re-render movie if data changed
                    movieAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Movies: " + movieList.size());

                } catch (JSONException e) {
                    // parse key might not exist or other issue when parsing json
                    Log.e(TAG, "Hit json exception", e);  // exceptions are all Throwable object
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }
}