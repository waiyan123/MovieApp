package itachi_waiyan.com.restapitest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.otto.Subscribe;

import java.util.List;

import itachi_waiyan.com.restapitest.App;
import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.adapter.DiscoverRecyclerAdapter;
import itachi_waiyan.com.restapitest.adapter.OnObjectSelectListener;
import itachi_waiyan.com.restapitest.adapter.ShowAllRecyclerAdapter;
import itachi_waiyan.com.restapitest.room.AppDatabase;
import itachi_waiyan.com.restapitest.room.entity.DiscoverMovieEntity;
import itachi_waiyan.com.restapitest.service.model.DiscoverMovies;
import itachi_waiyan.com.restapitest.service.model.NowPlayingResult;
import itachi_waiyan.com.restapitest.service.model.PopularResult;
import itachi_waiyan.com.restapitest.service.model.SearchResult;
import itachi_waiyan.com.restapitest.service.model.TopRatedResult;
import itachi_waiyan.com.restapitest.service.model.UpcomingResult;
import itachi_waiyan.com.restapitest.service.repository.ApiRequest;
import itachi_waiyan.com.restapitest.utils.BusProvider;

public class ShowAllMovieActivity extends AppCompatActivity implements View.OnClickListener {

    String pageKey;
    int pageNo,maxPage;
    ApiRequest apiRequest ;
    List<DiscoverMovies> moviesList;
    RecyclerView recyclerView;
    ShimmerFrameLayout shimmerFrameLayout;
    ShowAllRecyclerAdapter adapter;
    TextView tvPage;
    ImageView backArrow,frontArrow,imgSearch;
    EditText etSearch;
    AppDatabase room;
    boolean searchable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        room = App.getInstance().getDatabase();

        searchable = false;

        recyclerView = findViewById(R.id.show_all_recycler);
        shimmerFrameLayout = findViewById(R.id.shimmer_showall);
        backArrow = findViewById(R.id.back_arrow);
        frontArrow = findViewById(R.id.front_arrow);
        imgSearch = findViewById(R.id.img_search);
        etSearch = findViewById(R.id.et_search_box);
        tvPage = findViewById(R.id.tv_page);

        apiRequest = new ApiRequest();

        showAnim(shimmerFrameLayout,true);

        pageNo = 1;

        BusProvider.getInstance().register(this);

        pageKey = getIntent().getStringExtra("key");
        showMovies(pageNo);

        backArrow.setOnClickListener(this);
        frontArrow.setOnClickListener(this);
        imgSearch.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        showAnim(shimmerFrameLayout,true);
        switch (view.getId()){
            case R.id.back_arrow :
                if(pageNo==1){
                    return;
                }
                else {
                    pageNo--;
                    showMovies(pageNo);
                }
                break;
            case R.id.front_arrow :
                if(pageNo==maxPage){
                    return;
                }
                else {
                    pageNo++;
                    showMovies(pageNo);
                }
                break;
            case R.id.img_search :
                if(!searchable){
                    searchable = true;
                    etSearch.setVisibility(View.VISIBLE);
                    etSearch.requestFocus();
                }else {
                        pageKey = "search";
                        showMovies(pageNo);

                }
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
            case "search" : apiRequest.callSearchResult(pageNo,etSearch.getText().toString(),this);
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
                fetchByRoom(discoverMovies);
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(ShowAllMovieActivity.this,MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });

        maxPage = topRatedResult.getTotal_pages();
        showAnim(shimmerFrameLayout,false);
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
                fetchByRoom(discoverMovies);
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(ShowAllMovieActivity.this,MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        maxPage = popularResult.getTotal_pages();
        showAnim(shimmerFrameLayout,false);
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
                fetchByRoom(discoverMovies);
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(ShowAllMovieActivity.this,MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        maxPage = upcomingResult.getTotal_pages();
        showAnim(shimmerFrameLayout,false);
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
                fetchByRoom(discoverMovies);
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(ShowAllMovieActivity.this,MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        maxPage = nowPlayingResult.getTotal_pages();
        showAnim(shimmerFrameLayout,false);
        recyclerView.setAdapter(adapter);
    }

    @Subscribe
    public void getSearchMovies(SearchResult searchResult){
        moviesList = searchResult.getDiscoverMovies();
        if(moviesList.size()==0){
            Toast.makeText(this,"No result",Toast.LENGTH_SHORT).show();
        }
        Log.d("list","got it");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShowAllRecyclerAdapter(moviesList,this,false);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                fetchByRoom(discoverMovies);
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(ShowAllMovieActivity.this,MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        maxPage = searchResult.getTotal_pages();
        showAnim(shimmerFrameLayout,false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
        Log.d("home-----","destroy");
    }
    public void fetchByRoom(DiscoverMovies discoverMovies){
        DiscoverMovieEntity discoverMovieEntity = room.discoverMovieDao().fetchByVideoId(discoverMovies.getId());
        if(discoverMovieEntity==null){
            discoverMovieEntity = new DiscoverMovieEntity(discoverMovies.getId(),discoverMovies.getVote_count(),discoverMovies.isVideo(),discoverMovies.getMovieTitle(),discoverMovies.getPoster_url(),discoverMovies.getBackdrop_url(),discoverMovies.getMovieOverview(),discoverMovies.getRelease_date());
            room.discoverMovieDao().insertMovie(discoverMovieEntity);
            Log.d("db-----","added successful");
        }
        else Log.d("db----","already added");
    }
    private void showAnim(ShimmerFrameLayout shimmer,boolean anim) {
        if(anim){
            shimmer.setVisibility(View.VISIBLE);
            shimmer.startShimmerAnimation();
        }
        else {
            shimmer.setVisibility(View.GONE);
            shimmer.stopShimmerAnimation();
        }
    }
}
