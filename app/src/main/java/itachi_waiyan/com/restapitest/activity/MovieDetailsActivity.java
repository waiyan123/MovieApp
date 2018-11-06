package itachi_waiyan.com.restapitest.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.otto.Subscribe;

import itachi_waiyan.com.restapitest.ApiResponse.MovieDetails;
import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.adapter.CompanyRecyclerAdapter;
import itachi_waiyan.com.restapitest.rest.ApiRequest;
import itachi_waiyan.com.restapitest.utils.BusProvider;
import itachi_waiyan.com.restapitest.utils.Utils;

public class MovieDetailsActivity extends AppCompatActivity {

    ApiRequest apiRequest;
    ImageView bgImage,ppImg;
    TextView tvTitle,tvReleaseDate,tvStatus,tvOverview,tvTagline,tvBudget,tvRevenue;
    RecyclerView recyclerView;
    CompanyRecyclerAdapter comAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_layout);

        BusProvider.getInstance().register(this);

        bgImage = findViewById(R.id.bgImg);
        ppImg = findViewById(R.id.ppImg);
        tvTitle = findViewById(R.id.tvTitle);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        tvStatus = findViewById(R.id.tvStatus);
        tvOverview = findViewById(R.id.overview);
        tvTagline = findViewById(R.id.tvTagline);
        tvBudget = findViewById(R.id.tvBudget);
        tvRevenue = findViewById(R.id.tvRevenue);

        recyclerView = findViewById(R.id.productionCompaniesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        int id = getIntent().getExtras().getInt("movieId");

        apiRequest = new ApiRequest();
        apiRequest.callMovieDetails(id);

    }

    @Subscribe
    public void getMovieDetails(MovieDetails movieDetails){

        Glide.with(this)
                .load(Utils.IMG_PATH+movieDetails.getBackground_path())
                .placeholder(R.drawable.ic_launcher_background)
                .into(bgImage);

        Glide.with(this)
                .load(Utils.IMG_PATH+movieDetails.getPoster_path())
                .placeholder(R.drawable.ic_launcher_background)
                .into(ppImg);
        tvTitle.setText("Title - "+movieDetails.getTitle());
        tvReleaseDate.setText("Date - "+movieDetails.getRelease_date());
        tvStatus.setText("Status - "+movieDetails.getStatus());
        tvOverview.setText(movieDetails.getOverview());
        tvTagline.setText(movieDetails.getTagline());
        double budget = movieDetails.getBudget();
        String str = String.format("%,.2f", budget);
        tvBudget.setText("Budget   -  $"+str);

        double revenue = movieDetails.getRevenue();
        str = String.format("%,.2f", revenue);
        tvRevenue.setText("Revenue -  $"+str);

        comAdapter = new CompanyRecyclerAdapter(this,movieDetails.getProductCompanies());
        recyclerView.setAdapter(comAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }
}
