package itachi_waiyan.com.restapitest.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerUrls {
    @SerializedName("results")
    List<TrailerUrl> trailerUrlList;

    public List<TrailerUrl> getTrailerUrlList() {
        return trailerUrlList;
    }
}
