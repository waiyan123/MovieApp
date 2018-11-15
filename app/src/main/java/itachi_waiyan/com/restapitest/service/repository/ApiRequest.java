package itachi_waiyan.com.restapitest.service.repository;

import android.util.Log;

import itachi_waiyan.com.restapitest.rest.ApiClient;
import itachi_waiyan.com.restapitest.service.model.DiscoverResult;
import itachi_waiyan.com.restapitest.service.model.MovieDetails;
import itachi_waiyan.com.restapitest.service.model.TrailerUrls;
import itachi_waiyan.com.restapitest.service.repository.ApiInterface;
import itachi_waiyan.com.restapitest.utils.BusProvider;
import itachi_waiyan.com.restapitest.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRequest {

    static ApiInterface apiService;

    public ApiRequest(){
        if(apiService ==null){
            apiService = ApiClient.getClient().create(ApiInterface.class);
        }
    }

    public void callDiscoverResult(){
        Call<DiscoverResult>call = apiService.getDiscoverResult(Utils.API_KEY,5);
        call.enqueue(new Callback<DiscoverResult>() {
            @Override
            public void onResponse(Call<DiscoverResult> call, Response<DiscoverResult> response) {
                Log.d("response", String.valueOf(response.body().getTotal_pages()));
                BusProvider.getInstance().post(response.body());
            }

            @Override
            public void onFailure(Call<DiscoverResult> call, Throwable t) {

            }
        });
    }
    public void callMovieDetails(int d){
        Call<MovieDetails>call = apiService.getMovieDetails(d,Utils.API_KEY);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                BusProvider.getInstance().post(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });
    }
    public void callTrailerUrl(int id){
        Call<TrailerUrls>call = apiService.getTrailerUrls(id,Utils.API_KEY);
        call.enqueue(new Callback<TrailerUrls>() {
            @Override
            public void onResponse(Call<TrailerUrls> call, Response<TrailerUrls> response) {
                BusProvider.getInstance().post(response.body());
            }

            @Override
            public void onFailure(Call<TrailerUrls> call, Throwable t) {

            }
        });
    }
}
