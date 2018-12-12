package itachi_waiyan.com.restapitest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import itachi_waiyan.com.restapitest.service.model.DiscoverMovies;
import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.utils.Utils;

public class DiscoverRecyclerAdapter extends RecyclerView.Adapter<DiscoverRecyclerAdapter.DiscoverViewHolder> {

    private List<DiscoverMovies>moviesList ;
     Context context1;
    private OnObjectSelectListener onObjectSelectListener;
    boolean rated;

    public static class DiscoverViewHolder extends RecyclerView.ViewHolder {

        final View mView,llRated ;
        final TextView tvTitle,tvRated;
        ImageView imgPoster;

        public DiscoverViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            llRated = itemView.findViewById(R.id.ll_rated);
            tvRated = itemView.findViewById(R.id.tv_rate);
            tvTitle = itemView.findViewById(R.id.movieTitle);
            imgPoster = itemView.findViewById(R.id.posterImage);
        }
    }


    @NonNull
    @Override
    public DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_movie,parent,false);
        return new DiscoverViewHolder(view);
    }

    public DiscoverRecyclerAdapter(List<DiscoverMovies>movies, Context context,boolean rate){
        moviesList = movies;
        context1 = context;
        rated = rate;
    }
    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder holder, int position) {
        final DiscoverMovies discoverMovies = moviesList.get(position);

        Glide.with(context1)
                .load(Utils.IMG_PATH+discoverMovies.getPoster_url())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgPoster);

        holder.tvTitle.setText(discoverMovies.getMovieTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onObjectSelectListener!=null) onObjectSelectListener.onObjectSelected(discoverMovies);
            }
        });
        if(rated){
            holder.llRated.setVisibility(View.VISIBLE);
            holder.tvRated.setText(String.valueOf(discoverMovies.getVote_average()));
        }
        else holder.llRated.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setOnObjectSelectListener(OnObjectSelectListener onObjectSelectListener){
        this.onObjectSelectListener = onObjectSelectListener;
    }


}
