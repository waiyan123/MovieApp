package itachi_waiyan.com.restapitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.squareup.otto.Subscribe;

import java.util.List;

import itachi_waiyan.com.restapitest.ApiResponse.DiscoverMovies;
import itachi_waiyan.com.restapitest.ApiResponse.DiscoverResult;
import itachi_waiyan.com.restapitest.adapter.DiscoverRecyclerAdapter;
import itachi_waiyan.com.restapitest.rest.ApiRequest;
import itachi_waiyan.com.restapitest.utils.BusProvider;

public class MainActivity extends AppCompatActivity {

    ApiRequest apiRequest;
    RecyclerView recyclerView ;
    DiscoverRecyclerAdapter adapter;
    List<DiscoverMovies>moviesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BusProvider.getInstance().register(this);

        apiRequest = new ApiRequest();
        apiRequest.callDiscoverResult();


    }

    @Subscribe
    public void getDiscoverMovie(DiscoverResult discoverResult){
        moviesList = discoverResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerView = findViewById(R.id.discoverRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DiscoverRecyclerAdapter(moviesList,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }
}
