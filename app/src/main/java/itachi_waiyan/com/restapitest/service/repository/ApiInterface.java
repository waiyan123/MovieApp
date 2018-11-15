package itachi_waiyan.com.restapitest.service.repository;

import itachi_waiyan.com.restapitest.service.model.DiscoverResult;
import itachi_waiyan.com.restapitest.service.model.MovieDetails;
import itachi_waiyan.com.restapitest.service.model.TrailerUrls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET ("discover/movie")
    Call<DiscoverResult> getDiscoverResult(@Query("api_key") String api_key,@Query("page") int page);

    @GET ("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey);

    @GET ("movie/{movie_id}/videos")
    Call<TrailerUrls> getTrailerUrls(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey);

}