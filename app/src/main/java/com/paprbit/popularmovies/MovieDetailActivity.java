package com.paprbit.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.paprbit.popularmovies.net.retrofit.adapters.MovieAdapter;
import com.paprbit.popularmovies.net.retrofit.gson_pojo.Movie;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    private  static  final String IMAGE_URL_HEAD="http://image.tmdb.org/t/p/w500";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    Movie movie;
    @Bind(R.id.detail_image_view)
    ImageView detailImageView;
    @Bind(R.id.detail_tv_date)
    TextView tvDate;
    @Bind(R.id.detail_tv_overview)
    TextView tvOverview;
    @Bind(R.id.rating)
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movie = (Movie) bundle.getSerializable("movie_object");
        }

        if(movie!=null){
            setTitle(movie.getOriginal_title());
            tvDate.setText(movie.getRelease_date());
            tvOverview.setText(movie.getOverview());
            ratingBar.setRating(movie.getVote_average()/2);
            Picasso.with(MovieDetailActivity.this).load(IMAGE_URL_HEAD+movie.getPoster_path()).into(detailImageView);

        }

    }
}
