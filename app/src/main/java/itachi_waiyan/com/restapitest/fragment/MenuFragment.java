package itachi_waiyan.com.restapitest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.adapter.MyMenuAdapter;
import itachi_waiyan.com.restapitest.adapter.OnObjectSelectListener;

public class MenuFragment extends Fragment {


    MyMenuAdapter myMenuAdapter ;

    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu,container,true);


        recyclerView = view.findViewById(R.id.topRatedRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myMenuAdapter = new MyMenuAdapter();
        myMenuAdapter.setOnObjectSelectListener(new OnObjectSelectListener() {
            @Override
            public void onObjectSelected(Object selectedObj) {

            }
        });
        recyclerView.setAdapter(myMenuAdapter);

        return view;
    }
    public void showMenuList(){

    }
}
