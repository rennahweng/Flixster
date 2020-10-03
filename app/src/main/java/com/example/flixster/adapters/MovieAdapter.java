package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.MovieDetailActivity;
import com.example.flixster.R;
import com.example.flixster.databinding.ItemMovieBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

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
        // Store the binding
        ItemMovieBinding binding;

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

         // Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inflate the content view (replacing `setContentView`)
            binding = DataBindingUtil.setContentView((Activity) context, R.layout.item_movie);
            tvTitle = binding.tvTitle;
            tvOverview = binding.tvOverview;
            ivPoster = binding.ivPoster;
            container = binding.container;

//            tvTitle = itemView.findViewById(R.id.tvTitle);
//            tvOverview = itemView.findViewById(R.id.tvOverview);
//            ivPoster = itemView.findViewById(R.id.ivPoster);
//            container = itemView.findViewById(R.id.container);
        }

         public void bind(final Movie movie) {
            // Use the binding to update views directly on the binding
            binding.tvTitle.setText(movie.getTitle());
            binding.tvOverview.setText(movie.getOverview());

//            tvTitle.setText(movie.getTitle());
//            tvOverview.setText(movie.getOverview());

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

             // 1. Register click listener on the whole movie container on each row
             container.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     // Toast is a pop-up window on app screen
                     // Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();

                     // 2. Navigate to a new activity
                     // first parameter is the context, second is the class of the activity to launch
                     Intent i = new Intent(context, MovieDetailActivity.class);

                     // put "extras" into the bundle for access in the second activity
                     // Parcelable allows to break an object down and reconstruct on receiving activity
                     i.putExtra("movie", Parcels.wrap(movie));

                     // start the target activity by specifying a bundle of those shared elements and views
                     Pair<View, String> posterVideoPair = Pair.create((View) ivPoster, "posterToVideo");
                     Pair<View, String> titlePair = Pair.create((View) tvTitle, "movieTitle");
                     Pair<View, String> overviewPair = Pair.create((View) tvOverview, "movieOverview");
                     //noinspection unchecked
                     ActivityOptionsCompat options = ActivityOptionsCompat.
                             makeSceneTransitionAnimation((Activity) context, posterVideoPair, titlePair, overviewPair);

                     // brings up the second activity
                     context.startActivity(i, options.toBundle());
                 }
             });
         }
     }
}
