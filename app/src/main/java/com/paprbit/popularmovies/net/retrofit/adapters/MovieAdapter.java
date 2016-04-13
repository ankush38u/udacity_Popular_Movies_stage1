package com.paprbit.popularmovies.net.retrofit.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.paprbit.popularmovies.R;
import com.paprbit.popularmovies.net.retrofit.gson_pojo.Movie;
import com.paprbit.popularmovies.util.PixelDpConverter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    List<Movie> movieList;
    Movie movie = null;
    OnItemClickListener onItemClickListener;
    public  static  final String IMAGE_URL_HEAD="http://image.tmdb.org/t/p/w185";

    private Context context;

    public MovieAdapter() {
    }

    public MovieAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public MovieAdapter(Context context, OnItemClickListener onItemClickListener, List<Movie> movieList) {
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.movieList = movieList;
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_movie, parent, false);

        return new MovieHolder(itemView, onItemClickListener, movieList);


    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, int position) {
        movie = movieList.get(position);
        holder.tvMovieTitle.setText(movie.getTitle());
        int digit = (int)(Math.random()*3);  // would be 0 to 3
        Log.d("anki",String.valueOf(digit));
        switch (digit){
            case 0:
                holder.movieImage.setMinimumHeight(PixelDpConverter.dpToPx(260,context));
                break;
            case 1:
                holder.movieImage.setMinimumHeight(PixelDpConverter.dpToPx(330,context));
                break;
            case 2:
                holder.movieImage.setMinimumHeight(PixelDpConverter.dpToPx(370,context));
                break;
        }
        if(movie.getPoster_path()!=null && movie.getPoster_path().length()>0){
            holder.progressBar.setVisibility(View.VISIBLE);
            Picasso.with(context).load(IMAGE_URL_HEAD+movie.getPoster_path()).into(holder.movieImage, new Callback() {
                @Override
                public void onSuccess() {
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError() {
                    holder.progressBar.setVisibility(View.GONE);
                }
            });
        }
        holder.tvDate.setText(movie.getRelease_date());
    }


    @Override
    public int getItemCount() {
        return movieList.size();
        //hardcoded for now
    }


    public static class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView tvMovieTitle;
        protected ImageView movieImage;
        protected TextView tvDate;
        protected View rowParent;
        private OnItemClickListener onItemClickListener;
        private List<Movie> movieList;
        protected ProgressBar progressBar;

        public MovieHolder(View v, OnItemClickListener onItemClickListener, List<Movie> movieList) {
            super(v);
            tvMovieTitle = (TextView) v.findViewById(R.id.row_tv_title);
            movieImage = (ImageView) v.findViewById(R.id.row_image_view);
            tvDate = (TextView) v.findViewById(R.id.row_tv_release_date);
            rowParent = v.findViewById(R.id.row_parent);
            this.onItemClickListener = onItemClickListener;
            rowParent.setOnClickListener(this);
            this.movieList = movieList;
            progressBar = (ProgressBar) v.findViewById(R.id.progressbar);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(movieList.get(getPosition()));

        }
    }
}

