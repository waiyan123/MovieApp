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

import itachi_waiyan.com.restapitest.R;
import itachi_waiyan.com.restapitest.room.entity.DiscoverMovieEntity;
import itachi_waiyan.com.restapitest.service.model.DiscoverMovies;
import itachi_waiyan.com.restapitest.utils.Utils;

public class ShowAllRecyclerAdapter extends RecyclerView.Adapter<ShowAllRecyclerAdapter.ShowAllViewHolder> {

    private List<DiscoverMovies> moviesList ;
    private List<DiscoverMovieEntity>movieEntityList;
    Context context1;
    private OnObjectSelectListener onObjectSelectListener;
    boolean rated;

    public static class ShowAllViewHolder extends RecyclerView.ViewHolder {

        final View mView,llRated ;
        final TextView tvTitle,tvRated,tvReleaseDate;
        ImageView imgPoster;

        public ShowAllViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            llRated = itemView.findViewById(R.id.ll_rated);
            tvRated = itemView.findViewById(R.id.tv_rate);
            tvTitle = itemView.findViewById(R.id.movieTitle);
            imgPoster = itemView.findViewById(R.id.posterImage);
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
        }
    }

    public ShowAllRecyclerAdapter (List<DiscoverMovies>movies, Context context,boolean rate){
        moviesList = movies;
        context1 = context;
        rated = rate;
    }
    public ShowAllRecyclerAdapter(List<DiscoverMovieEntity>movies,Context context){
        movieEntityList = movies;
        context1 = context;
        rated = false;
    }

    @NonNull
    @Override
    public ShowAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_show_all_movies,parent,false);
        return new ShowAllViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllViewHolder holder, int position) {

        if(moviesList!=null) {
            final DiscoverMovies discoverMovies = moviesList.get(position);

            Glide.with(context1)
                    .load(Utils.IMG_PATH + discoverMovies.getPoster_url())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.imgPoster);

            holder.tvTitle.setText(discoverMovies.getMovieTitle());
            holder.tvReleaseDate.setText(context1.getString(R.string.release_date) + discoverMovies.getRelease_date());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onObjectSelectListener != null)
                        onObjectSelectListener.onObjectSelected(discoverMovies);
                }
            });
            if (rated) {
                holder.llRated.setVisibility(View.VISIBLE);
                holder.tvRated.setText(String.valueOf(discoverMovies.getVote_average()));
            } else holder.llRated.setVisibility(View.GONE);
        }
        else if(movieEntityList!=null){
            final DiscoverMovieEntity discoverMovieEntity = movieEntityList.get(position);
            Glide.with(context1)
                    .load(Utils.IMG_PATH + discoverMovieEntity.getPoster_url())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.imgPoster);

            holder.tvTitle.setText(discoverMovieEntity.getMovie_title());
            holder.tvReleaseDate.setText(context1.getString(R.string.release_date) + discoverMovieEntity.getRelease_date());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onObjectSelectListener != null)
                        onObjectSelectListener.onObjectSelected(discoverMovieEntity);
                }
            });

            if (rated) {
                holder.llRated.setVisibility(View.VISIBLE);
                holder.tvRated.setText(String.valueOf(discoverMovieEntity.getVote_count()));
            } else holder.llRated.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        if(movieEntityList!=null){
            itemCount = movieEntityList.size();
        }
        else if(moviesList!=null){
            itemCount = moviesList.size();
        }
        return itemCount;
    }
    public void setOnObjectSelectListener(OnObjectSelectListener onObjectSelectListener){
        this.onObjectSelectListener = onObjectSelectListener;
    }
}
