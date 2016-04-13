package com.paprbit.popularmovies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.paprbit.popularmovies.net.retrofit.ServiceGenerator;
import com.paprbit.popularmovies.net.retrofit.adapters.MovieAdapter;
import com.paprbit.popularmovies.net.retrofit.gson_pojo.Movie;
import com.paprbit.popularmovies.net.retrofit.gson_pojo.MovieResponse;
import com.paprbit.popularmovies.util.UserInteraction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MoviesActivity extends AppCompatActivity implements MovieAdapter.OnItemClickListener {
    @Bind(R.id.recView)
    RecyclerView recView;
    private ProgressDialog pd;
    @Bind(R.id.parent_layout)
    CoordinatorLayout parentLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.sort_spinner)
    Spinner spinner;
    private List<Movie> movieList;

    private MovieAdapter movieAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        pd = new ProgressDialog(this);
        pd.setMessage(getString(R.string.pd_message));
        pd.setCancelable(false);
        setSupportActionBar(toolbar);
        loadPopular();




        ArrayList<String> list = new ArrayList<>();
        list.add("Sort By");
        list.add("Popular");
        list.add("Top Rated");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row_spn, list);
        adapter.setDropDownViewResource(R.layout.row_spn_dropdown);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(((String)spinner.getSelectedItem()).equalsIgnoreCase("Popular")){
                    loadPopular();
                }else if(((String)spinner.getSelectedItem()).equalsIgnoreCase("Top Rated")){
                    loadTopRated();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }


    private void loadPopular(){
        Call<MovieResponse> call = ServiceGenerator.getService().getMoviesPopular(getString(R.string.moviedb_api_key));
        pd.show();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Response<MovieResponse> response, Retrofit retrofit) {
                pd.hide();
                if (response.isSuccess()) {
                    MovieAdapter movieAdapter = null;
                    movieAdapter = new MovieAdapter(MoviesActivity.this, MoviesActivity.this, response.body().getResults());
                    recView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    recView.setAdapter(movieAdapter);
                    recView.setHasFixedSize(false);
                } else {
                    UserInteraction.makeSnack(parentLayout, response.raw().message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                pd.hide();
                UserInteraction.makeSnack(parentLayout, getString(R.string.network_problem_message));
            }
        });
    }

    private void loadTopRated(){
        Call<MovieResponse> call = ServiceGenerator.getService().getMoviesTopRated(getString(R.string.moviedb_api_key));
        pd.show();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Response<MovieResponse> response, Retrofit retrofit) {
                pd.hide();
                if (response.isSuccess()) {
                    movieList= response.body().getResults();
                    movieAdapter = new MovieAdapter(MoviesActivity.this, MoviesActivity.this, movieList);
                    recView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    recView.setAdapter(movieAdapter);
                    recView.setHasFixedSize(false);
                } else {
                    UserInteraction.makeSnack(parentLayout, response.raw().message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                pd.hide();
                UserInteraction.makeSnack(parentLayout, getString(R.string.network_problem_message));
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(movieList!=null){
            outState.putSerializable("list", (Serializable) movieList);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        movieList = (List<Movie>) savedInstanceState.getSerializable("list");
        if(movieList!=null){
            movieAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(Movie movie) {
        Intent intent = new Intent(this,MovieDetailActivity.class).putExtra("movie_object",movie);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this,(ImageView)findViewById(R.id.row_image_view) , getString(R.string.activity_image_trans));
            startActivity(intent, options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(pd!= null)
            pd.dismiss();
    }
}
