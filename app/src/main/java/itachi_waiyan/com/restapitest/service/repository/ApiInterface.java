package itachi_waiyan.com.restapitest.service.repository;

import itachi_waiyan.com.restapitest.service.model.SearchResult;
import itachi_waiyan.com.restapitest.service.model.TopRatedResult;
import itachi_waiyan.com.restapitest.service.model.MovieDetails;
import itachi_waiyan.com.restapitest.service.model.NowPlayingResult;
import itachi_waiyan.com.restapitest.service.model.PopularResult;
import itachi_waiyan.com.restapitest.service.model.TrailerUrls;
import itachi_waiyan.com.restapitest.service.model.UpcomingResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET ("movie/top_rated")
    Call<TopRatedResult> getDiscoverResult(@Query("api_key") String api_key, @Query("page") int page);

    @GET ("movie/popular")
    Call<PopularResult>getPopularResult(@Query("api_key") String api_key, @Query("page") int page);

    @GET ("movie/now_playing")
    Call<NowPlayingResult>getNowPlayingResult(@Query("api_key") String api_key, @Query("page") int page);

    @GET ("movie/upcoming")
    Call<UpcomingResult>getUpcomingResult(@Query("api_key") String api_key, @Query("page") int page);

    @GET ("search/movie")
    Call<SearchResult>getSearchResult (@Query("api_key") String api_key, @Query("page") int page, @Query("query") String query);

    @GET ("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey);

    @GET ("movie/{movie_id}/videos")
    Call<TrailerUrls> getTrailerUrls(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey);

}
