package itachi_waiyan.com.restapitest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import itachi_waiyan.com.restapitest.ApiResponse.DiscoverMovies;
import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.utils.Utils;

public class DiscoverRecyclerAdapter extends RecyclerView.Adapter<DiscoverRecyclerAdapter.DiscoverViewHolder> {

    private List<DiscoverMovies>moviesList ;
     Context context1;
    private OnObjectSelectListener onObjectSelectListener;

    public static class DiscoverViewHolder extends RecyclerView.ViewHolder {

        final View mView ;
        final TextView tvTitle,tvReleaseDate,tvOverview;
        ImageView imgPoster;

        public DiscoverViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvTitle = itemView.findViewById(R.id.movieTitle);
            tvReleaseDate = itemView.findViewById(R.id.releaseDate);
            tvOverview = itemView.findViewById(R.id.overview);
            imgPoster = itemView.findViewById(R.id.posterImage);
        }
    }


    @NonNull
    @Override
    public DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_discover,parent,false);
        return new DiscoverViewHolder(view);
    }

    public DiscoverRecyclerAdapter(List<DiscoverMovies>movies, Context context){
        moviesList = movies;
        context1 = context;
    }
    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder holder, int position) {
        final DiscoverMovies discoverMovies = moviesList.get(position);

        Glide.with(context1)
                .load(Utils.IMG_PATH+discoverMovies.getPoster_url())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgPoster);

        holder.tvTitle.setText(discoverMovies.getMovieTitle());
        holder.tvReleaseDate.setText(discoverMovies.getRelease_date());
        holder.tvOverview.setText(discoverMovies.getMovieOverview());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onObjectSelectListener!=null) onObjectSelectListener.onObjectSelected(discoverMovies);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setOnObjectSelectListener(OnObjectSelectListener onObjectSelectListener){
        this.onObjectSelectListener = onObjectSelectListener;
    }


}
