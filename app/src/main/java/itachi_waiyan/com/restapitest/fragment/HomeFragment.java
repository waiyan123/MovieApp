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

import com.squareup.otto.Subscribe;

import java.util.List;

import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.activity.MovieDetailsActivity;
import itachi_waiyan.com.restapitest.adapter.DiscoverRecyclerAdapter;
import itachi_waiyan.com.restapitest.adapter.OnObjectSelectListener;
import itachi_waiyan.com.restapitest.service.model.DiscoverMovies;
import itachi_waiyan.com.restapitest.service.model.TopRatedResult;
import itachi_waiyan.com.restapitest.service.model.NowPlayingResult;
import itachi_waiyan.com.restapitest.service.model.PopularResult;
import itachi_waiyan.com.restapitest.service.model.UpcomingResult;
import itachi_waiyan.com.restapitest.service.repository.ApiRequest;
import itachi_waiyan.com.restapitest.utils.BusProvider;

public class HomeFragment extends Fragment {

    ApiRequest apiRequest;
    RecyclerView recyclerView ;
    DiscoverRecyclerAdapter adapter;
    List<DiscoverMovies> moviesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiRequest = new ApiRequest();
        apiRequest.callTopRatedResult(1);
        apiRequest.callPopularResult(2);
        apiRequest.callNowPlayingResult(1);
        apiRequest.callUpcomingResult(1);

        Log.d("home-----","create");

        BusProvider.getInstance().register(this);
    }

    @Subscribe
    public void getTopRatedMovie(TopRatedResult topRatedResult){
        moviesList = topRatedResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerView = getView().findViewById(R.id.topRatedRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new DiscoverRecyclerAdapter(moviesList,getContext(),true);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(getContext(),MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Subscribe
    public void getPopularMovie(PopularResult popularResult){
        moviesList = popularResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerView = getView().findViewById(R.id.popularRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new DiscoverRecyclerAdapter(moviesList,getContext(),false);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(getContext(),MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
    @Subscribe
    public void getUpcomingMovie(UpcomingResult upcomingResult){
        moviesList = upcomingResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerView = getView().findViewById(R.id.upcomingRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new DiscoverRecyclerAdapter(moviesList,getContext(),false);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(getContext(),MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Subscribe
    public void getNowPlayingMovie(NowPlayingResult nowPlayingResult){
        moviesList = nowPlayingResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerView = getView().findViewById(R.id.nowPlayingRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new DiscoverRecyclerAdapter(moviesList,getContext(),false);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(getContext(),MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
        Log.d("home-----","destroy");
    }
}
