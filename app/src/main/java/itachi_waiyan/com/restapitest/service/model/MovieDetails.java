package itachi_waiyan.com.restapitest.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {

    @SerializedName("adult")
    boolean adult;

    @SerializedName("backdrop_path")
    String background_path;

    @SerializedName("budget")
    int budget;

    @SerializedName("homepage")
    String homepageUrl;

    @SerializedName("id")
    int id;

    @SerializedName("imdb_id")
    String imdb_id;

    @SerializedName("original_title")
    String original_title;

    @SerializedName("overview")
    String overview;

    @SerializedName("poster_path")
    String poster_path;

    @SerializedName("production_companies")
    List<ProductCompany>productCompanies;

    @SerializedName("release_date")
    String release_date;

    @SerializedName("revenue")
    int revenue;

    @SerializedName("runtime")
    int runtime;

    @SerializedName("status")
    String status;

    @SerializedName("tagline")
    String tagline;

    @SerializedName("title")
    String title;

    @SerializedName("vote_average")
    double vote_average;

    @SerializedName("vote_count")
    int vote_count;

    public boolean isAdult() {
        return adult;
    }

    public String getBackground_path() {
        return background_path;
    }

    public int getBudget() {
        return budget;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public int getId() {
        return id;
    }

    public String getImdb_id() {
        return imdb_id;
    }


    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

//    public double getPopularity() {
//        return popularity;
//    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getRevenue() {
        return revenue;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getTitle() {
        return title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public List<ProductCompany> getProductCompanies() {
        return productCompanies;
    }
}
