package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MovieDetailActivity extends YouTubeBaseActivity {

    public static final String YOUTUBE_API_KEY = "AIzaSyBWySv6L813ONx3TorGUaEuPz1_UdTd7RQ";
    public static final String VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;
    YouTubePlayerView youtubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        ratingBar = findViewById(R.id.ratingBar);
        youtubePlayerView  =findViewById(R.id.youtubePlayer);

        // Once data is added to intent bundle, pull whole movie object from bundle
        // then pull specific data and populate them
        // On the receiving side, we need to unwrap the object:
        Movie movie = Parcels.unwrap( getIntent().getParcelableExtra("movie") );
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        ratingBar.setRating( (float) movie.getRating() );

        // Make HTTP request to movie databases api to extract videos
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEOS_URL, 209112), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    // check if we get an non-empty result back from api
                    if (results.length() == 0) {
                        return; // do nothing
                    } else {
                        String youtubeKey = results.getJSONObject(0).getString("key");
                        Log.e("MovieDetailActivity", youtubeKey);
                    }
                } catch (JSONException e) {
                    Log.e("MovieDetailActivity", "Failed to parse JSON", e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });



        // Add movie trailer in youtube player view
        youtubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                // DEBUG in Logcat
                Log.d("MovieDetailActivity", "onInitializationSuccess");

                // Extract video id of the movies from movie database api,
                // then cue video, play video, or other actions
                youTubePlayer.cueVideo("5xVh-7ywKpE");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                // DEBUG in Logcat
                Log.d("MovieDetailActivity", "onInitializationFailure");
            }
        });
    }
}