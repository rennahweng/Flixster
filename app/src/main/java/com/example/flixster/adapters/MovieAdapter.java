package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.telecom.Conference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    // Constructor
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // 3 Methods inherited from Abstract class - RecyclerView.Adapter:

    // 1. Usually involves inflating a layout from XML and returning holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");

        // Inflate the custom layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);

        // Return a new holder instance of movie
        return new ViewHolder(movieView);
    }

    // 2. Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);

        // get the movie at the position
        Movie movie = movies.get(position);
        // bind the movie data into ViewHolder
        holder.bind(movie);
    }

    // 3. Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }


     // Nested class - ViewHolder
    // ViewHolder is a representation of our row in the RecycleView
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        // Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

         public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            // To have different poster path for different orientation (portrait vs landscape)
             String imageUrl;
             if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                 imageUrl = movie.getBackdropPath();

             } else {
                 imageUrl = movie.getPosterPath();
             }

             // load poster image using Glide
             Glide.with(context)
                     .load(imageUrl)
                     .placeholder(R.drawable.placeholder) // default image for unloaded posters
                     .fitCenter() // scale to fit entire image within ImageView
                     .transform(new RoundedCornersTransformation(25, 0))
                     .into(ivPoster);
         }
     }
}
