package itachi_waiyan.com.restapitest.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity (tableName = "discover_movie_table")
public class DiscoverMovieEntity {
    @PrimaryKey (autoGenerate = true)
    int id;

    @ColumnInfo (name = "vote_count")
    int vote_count;

    @ColumnInfo(name = "video_id")
    int video_id;

    @ColumnInfo (name = "boolean_video")
    boolean boolean_video;

    @ColumnInfo(name = "movie_title")
    String movie_title;

    @ColumnInfo(name = "poster_path")
    String poster_url;

    @ColumnInfo(name = "backdrop_path")
    String backdrop_url;

    @ColumnInfo(name = "movie_overview")
    String MovieOverview;

    @ColumnInfo(name = "release_date")
    String release_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
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
        return MovieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        MovieOverview = movieOverview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
