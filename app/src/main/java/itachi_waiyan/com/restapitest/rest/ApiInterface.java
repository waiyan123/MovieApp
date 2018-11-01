package itachi_waiyan.com.restapitest.rest;

import itachi_waiyan.com.restapitest.ApiResponse.DiscoverResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET ("discover/movie")
    Call<DiscoverResult> getDiscoverResult(@Query("api_key") String api_key);

//    @GET ("configuration")
//    Call<> getImageConfig(@Query("api_key") String api_key);

}
