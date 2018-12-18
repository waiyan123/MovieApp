package itachi_waiyan.com.restapitest.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity (tableName = "discover_movie_table")
public class DiscoverMovieEntity {

    @PrimaryKey
    @ColumnInfo(name = "video_id")
    @NonNull
    public int videoId;

    @ColumnInfo (name = "vote_count")
    int vote_count;

    @ColumnInfo (name = "boolean_video")
    boolean boolean_video;

    @ColumnInfo(name = "movie_title")
    String movie_title;

    @ColumnInfo(name = "poster_path")
    String poster_url;

    @ColumnInfo(name = "backdrop_path")
    String backdrop_url;

    @ColumnInfo(name = "movie_overview")
    String movie_overview;

    @ColumnInfo(name = "release_date")
    String release_date;

    public DiscoverMovieEntity(@NonNull int videoId, int vote_count, boolean boolean_video, String movie_title, String poster_url, String backdrop_url, String movie_overview, String release_date) {
        this.videoId = videoId;
        this.vote_count = vote_count;
        this.boolean_video = boolean_video;
        this.movie_title = movie_title;
        this.poster_url = poster_url;
        this.backdrop_url = backdrop_url;
        this.movie_overview = movie_overview;
        this.release_date = release_date;
    }

    @NonNull
    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(@NonNull int videoId) {
        this.videoId = videoId;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public void setMovie_overview(String movie_overview) {
        this.movie_overview = movie_overview;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isBoolean_video() {
        return boolean_video;
    }

    public void setBoolean_video(boolean boolean_video) {
        this.boolean_video = boolean_video;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getBackdrop_url() {
        return backdrop_url;
    }

    public void setBackdrop_url(String backdrop_url) {
        this.backdrop_url = backdrop_url;
    }

    public String getMovieOverview() {
        return movie_overview;
    }

    public void setMovieOverview(String movieOverview) {
        movieOverview = movieOverview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
