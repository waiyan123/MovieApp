package itachi_waiyan.com.restapitest.service.model;

import com.google.gson.annotations.SerializedName;

public class DiscoverMovies {

    @SerializedName("vote_count")
    int vote_count;

    @SerializedName("id")
    int id;

    @SerializedName("video")
    boolean video;

    @SerializedName("vote_average")
    double vote_average;

    @SerializedName("title")
    String movieTitle;

    @SerializedName("poster_path")
    String poster_url;

    @SerializedName("backdrop_path")
    String backdrop_url;

    @SerializedName("overview")
    String MovieOverview;

    @SerializedName("release_date")
    String release_date;

    public int getVote_count() {
        return vote_count;
    }

    public int getId() {
        return id;
    }


    public boolean isVideo() {
        return video;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public String getBackdrop_url() {
        return backdrop_url;
    }

    public String getMovieOverview() {
        return MovieOverview;
    }

    public String getRelease_date() {
        return release_date;
    }
}
