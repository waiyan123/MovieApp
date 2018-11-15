package itachi_waiyan.com.restapitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.squareup.otto.Subscribe;

import java.util.List;

import itachi_waiyan.com.restapitest.service.model.DiscoverMovies;
import itachi_waiyan.com.restapitest.service.model.DiscoverResult;
import itachi_waiyan.com.restapitest.activity.MovieDetailsActivity;
import itachi_waiyan.com.restapitest.adapter.DiscoverRecyclerAdapter;
import itachi_waiyan.com.restapitest.adapter.OnObjectSelectListener;
import itachi_waiyan.com.restapitest.service.repository.ApiRequest;
import itachi_waiyan.com.restapitest.utils.BottomLayoutHelper;
import itachi_waiyan.com.restapitest.utils.BusProvider;

public class MainActivity extends AppCompatActivity {

    ApiRequest apiRequest;
    RecyclerView recyclerView ;
    DiscoverRecyclerAdapter adapter;
    List<DiscoverMovies>moviesList;
    BottomLayoutHelper bottomLayoutHelper;
    BottomLayoutHelper.BottomTabLayoutHelperSelectListener bottomTabLayoutHelperSelectListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BusProvider.getInstance().register(this);

        apiRequest = new ApiRequest();
        apiRequest.callDiscoverResult();

        bottomTabLayoutHelperSelectListener = new BottomLayoutHelper.BottomTabLayoutHelperSelectListener() {
            @Override
            public void selected(int i) {

            }
        };
        bottomLayoutHelper = BottomLayoutHelper.withThis(this,bottomTabLayoutHelperSelectListener).setUp((LinearLayout) findViewById(R.id.bottomTab));



    }

    @Subscribe
    public void getDiscoverMovie(DiscoverResult discoverResult){
        moviesList = discoverResult.getDiscoverMovies();
        Log.d("list","got it");
        recyclerView = findViewById(R.id.discoverRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DiscoverRecyclerAdapter(moviesList,this);
        adapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovies discoverMovies = (DiscoverMovies) selectedObj;
                int id = discoverMovies.getId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(MainActivity.this,MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }
}
