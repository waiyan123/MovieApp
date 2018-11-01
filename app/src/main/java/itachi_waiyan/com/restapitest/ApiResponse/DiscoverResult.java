package itachi_waiyan.com.restapitest.ApiResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import itachi_waiyan.com.restapitest.room.entity.DiscoverMovieEntity;

public class DiscoverResult {

    @SerializedName("page")
    int page;

    @SerializedName("total_results")
    int total_results;

    @SerializedName("total_pages")
    int total_pages;

    @SerializedName("results")
    List<DiscoverMovies>discoverMovies;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<DiscoverMovies> getDiscoverMovies() {
        return discoverMovies;
    }

    public void setDiscoverMovies(List<DiscoverMovies> discoverMovies) {
        this.discoverMovies = discoverMovies;
    }
}
