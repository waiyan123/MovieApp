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

import java.util.List;

import itachi_waiyan.com.restapitest.App;
import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.activity.MovieDetailsActivity;
import itachi_waiyan.com.restapitest.adapter.OnObjectSelectListener;
import itachi_waiyan.com.restapitest.adapter.ShowAllRecyclerAdapter;
import itachi_waiyan.com.restapitest.room.AppDatabase;
import itachi_waiyan.com.restapitest.room.entity.DiscoverMovieEntity;

public class RecentFragment extends Fragment {

    RecyclerView recyclerView;
    ShowAllRecyclerAdapter showAllRecyclerAdapter;
    List<DiscoverMovieEntity>discoverMovieEntitiesList;
    AppDatabase room;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent,container,false);
        recyclerView = view.findViewById(R.id.favourite_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        room = App.getInstance().getDatabase();
        discoverMovieEntitiesList = room.discoverMovieDao().getMovieList();
        showAllRecyclerAdapter = new ShowAllRecyclerAdapter(discoverMovieEntitiesList,getContext());
        showAllRecyclerAdapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {
                DiscoverMovieEntity discoverMovieEntity = (DiscoverMovieEntity)selectedObj;
                int id = discoverMovieEntity.getVideoId();
                Log.d("selectedId", String.valueOf(id));
                Intent intent = new Intent(getContext(),MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                startActivity(intent);
            }
        });


        recyclerView.setAdapter(showAllRecyclerAdapter);

        return view;

    }
}
