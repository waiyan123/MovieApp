package itachi_waiyan.com.restapitest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.otto.Subscribe;

import java.util.List;

import itachi_waiyan.com.restapitest.App;
import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.activity.MovieDetailsActivity;
import itachi_waiyan.com.restapitest.activity.ShowAllMovieActivity;
import itachi_waiyan.com.restapitest.adapter.DiscoverRecyclerAdapter;
import itachi_waiyan.com.restapitest.adapter.OnObjectSelectListener;
import itachi_waiyan.com.restapitest.room.AppDatabase;
import itachi_waiyan.com.restapitest.room.entity.DiscoverMovieEntity;
import itachi_waiyan.com.restapitest.service.model.DiscoverMovies;
import itachi_waiyan.com.restapitest.service.model.TopRatedResult;
import itachi_waiyan.com.restapitest.service.model.NowPlayingResult;
import itachi_waiyan.com.restapitest.service.model.PopularResult;
import itachi_waiyan.com.restapitest.service.model.UpcomingResult;
import itachi_waiyan.com.restapitest.service.repository.ApiRequest;
import itachi_waiyan.com.restapitest.utils.BusProvider;

public class HomeFragment extends Fragment implements View.OnClickListener {

    ApiRequest apiRequest;
    RecyclerView recyclerViewTopRated,recyclerViewPopular,recyclerViewNowPlaying,recyclerViewUpcoming ;
    ShimmerFrameLayout shimmerTopRated,shimmerPopular,shimmerNowPlaying,shimmerUpcoming;
    DiscoverRecyclerAdapter adapter;
    List<DiscoverMovies> moviesList;
    View topRateShow,popularShow,nowPlayingShow,upcomingShow;
    AppDatabase room;
    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_home,container,false);
        recyclerViewTopRated = mView.findViewById(R.id.topRatedRecycler);
        recyclerViewPopular = mView.findViewById(R.id.popularRecycler);
        recyclerViewNowPlaying = mView.findViewById(R.id.nowPlayingRecycler);
        recyclerViewUpcoming = mView.findViewById(R.id.upcomingRecycler);
        shimmerTopRated = mView.findViewById(R.id.shimmer_top_rated);
        shimmerPopular = mView.findViewById(R.id.shimmer_popular);
        shimmerNowPlaying = mView.findViewById(R.id.shimmer_nowplaying);
        shimmerUpcoming = mView.findViewById(R.id.shimmer_upcoming);
        shimmerTopRated.startShimmerAnimation();
        shimmerPopular.startShimmerAnimation();
        shimmerNowPlaying.startShimmerAnimation();
        shimmerUpcoming.startShimmerAnimation();
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiRequest = new ApiRequest();

        apiRequest.callTopRatedResult(1);
        apiRequest.callPopularResult(2);
        apiRequest.callNowPlayingResult(1);
        apiRequest.callUpcomingResult(1);

        room = App.getInstance().getDatabase();
        Log.d("home-----","create");

        BusProvider.getInstance().register(this);

    }

    @Subscribe
    public void getTopRatedMovie(TopRatedResult topRatedResult){
        moviesList = topRatedResult.getDiscoverMovies();

        Log.d("list","got it");
        recyclerViewTopRated = getView().findViewById(R.id.topRatedRecycler);
        showAnim(shimmerTopRated,false);
        recyclerViewTopRated.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new DiscoverRecyclerAdapter(moviesList,getContext(),true);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                fetchByRoom(discoverMovies);
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(getContext(),MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        recyclerViewTopRated.setAdapter(adapter);
        recyclerViewTopRated.setVisibility(View.VISIBLE);
        topRateShow = getView().findViewById(R.id.top_rate_show_all);
        topRateShow.setOnClickListener(this);
    }

    @Subscribe
    public void getPopularMovie(PopularResult popularResult){
        moviesList = popularResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerViewPopular = getView().findViewById(R.id.popularRecycler);
        showAnim(shimmerPopular,false);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new DiscoverRecyclerAdapter(moviesList,getContext(),false);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                fetchByRoom(discoverMovies);
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(getContext(),MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        recyclerViewPopular.setAdapter(adapter);
        recyclerViewPopular.setVisibility(View.VISIBLE);
        popularShow = getView().findViewById(R.id.popular_show_all);
        popularShow.setOnClickListener(this);
    }
    @Subscribe
    public void getUpcomingMovie(UpcomingResult upcomingResult){
        moviesList = upcomingResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerViewUpcoming = getView().findViewById(R.id.upcomingRecycler);
        showAnim(shimmerUpcoming,false);
        recyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new DiscoverRecyclerAdapter(moviesList,getContext(),false);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                fetchByRoom(discoverMovies);
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(getContext(),MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        recyclerViewUpcoming.setAdapter(adapter);
        recyclerViewUpcoming.setVisibility(View.VISIBLE);
        nowPlayingShow = getView().findViewById(R.id.now_playing_show_all);
        nowPlayingShow.setOnClickListener(this);
    }

    @Subscribe
    public void getNowPlayingMovie(NowPlayingResult nowPlayingResult){
        moviesList = nowPlayingResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerViewNowPlaying = getView().findViewById(R.id.nowPlayingRecycler);
        showAnim(shimmerNowPlaying,false);
        recyclerViewNowPlaying.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new DiscoverRecyclerAdapter(moviesList,getContext(),false);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                fetchByRoom(discoverMovies);
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(getContext(),MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        recyclerViewNowPlaying.setAdapter(adapter);
        recyclerViewNowPlaying.setVisibility(View.VISIBLE);
        upcomingShow = getView().findViewById(R.id.upcoming_show_all);
        upcomingShow.setOnClickListener(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
        Log.d("home-----","destroy");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(),ShowAllMovieActivity.class);
        switch (view.getId()){
            case R.id.top_rate_show_all :
                intent.putExtra("key","topRated");
                startActivity(intent);
                break;
            case R.id.popular_show_all :
                intent.putExtra("key","popular");
                startActivity(intent);
                break;
            case R.id.now_playing_show_all :
                intent.putExtra("key","nowPlaying");
                startActivity(intent);
                break;
            case R.id.upcoming_show_all :
                intent.putExtra("key","upcoming");
                startActivity(intent);
                break;
        }
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
