package itachi_waiyan.com.restapitest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.List;

import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.adapter.DiscoverRecyclerAdapter;
import itachi_waiyan.com.restapitest.adapter.OnObjectSelectListener;
import itachi_waiyan.com.restapitest.adapter.ShowAllRecyclerAdapter;
import itachi_waiyan.com.restapitest.service.model.DiscoverMovies;
import itachi_waiyan.com.restapitest.service.model.NowPlayingResult;
import itachi_waiyan.com.restapitest.service.model.PopularResult;
import itachi_waiyan.com.restapitest.service.model.TopRatedResult;
import itachi_waiyan.com.restapitest.service.model.UpcomingResult;
import itachi_waiyan.com.restapitest.service.repository.ApiRequest;
import itachi_waiyan.com.restapitest.utils.BusProvider;

public class ShowAllMovieActivity extends AppCompatActivity implements View.OnClickListener {

    String pageKey;
    int minPage,maxPage;
    ApiRequest apiRequest ;
    List<DiscoverMovies> moviesList;
    RecyclerView recyclerView;
    ShowAllRecyclerAdapter adapter;
    TextView tvPage;
    ImageView backArrow,frontArrow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        recyclerView = findViewById(R.id.show_all_recycler);
        backArrow = findViewById(R.id.back_arrow);
        frontArrow = findViewById(R.id.front_arrow);
        tvPage = findViewById(R.id.tv_page);

        apiRequest = new ApiRequest();

        minPage = 1;

        BusProvider.getInstance().register(this);

        pageKey = getIntent().getStringExtra("key");
        showMovies(minPage);

        backArrow.setOnClickListener(this);
        frontArrow.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_arrow :
                if(minPage>0 && minPage<maxPage){

                }
                break;
            case R.id.front_arrow :
                break;
        }
    }

    public void showMovies(int page){
        tvPage.setText(getString(R.string.page)+page);
        switch (pageKey){
            case "topRated" : apiRequest.callTopRatedResult(page);
                break;
            case "popular" : apiRequest.callPopularResult(page);
                break;
            case "nowPlaying" : apiRequest.callNowPlayingResult(page);
                break;
            case "upcoming" : apiRequest.callUpcomingResult(page);
                break;
        }
    }

    @Subscribe
    public void getTopRatedMovie(TopRatedResult topRatedResult){
        moviesList = topRatedResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShowAllRecyclerAdapter(moviesList,this,true);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(ShowAllMovieActivity.this,MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });

        maxPage = topRatedResult.getTotal_pages();
        recyclerView.setAdapter(adapter);

    }

    @Subscribe
    public void getPopularMovie(PopularResult popularResult){
        moviesList = popularResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShowAllRecyclerAdapter(moviesList,this,false);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(ShowAllMovieActivity.this,MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        maxPage = popularResult.getTotal_pages();
        recyclerView.setAdapter(adapter);
    }
    @Subscribe
    public void getUpcomingMovie(UpcomingResult upcomingResult){
        moviesList = upcomingResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShowAllRecyclerAdapter(moviesList,this,false);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(ShowAllMovieActivity.this,MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        maxPage = upcomingResult.getTotal_pages();
        recyclerView.setAdapter(adapter);
    }

    @Subscribe
    public void getNowPlayingMovie(NowPlayingResult nowPlayingResult){
        moviesList = nowPlayingResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShowAllRecyclerAdapter(moviesList,this,false);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(ShowAllMovieActivity.this,MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        maxPage = nowPlayingResult.getTotal_pages();
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
        Log.d("home-----","destroy");
    }
}
