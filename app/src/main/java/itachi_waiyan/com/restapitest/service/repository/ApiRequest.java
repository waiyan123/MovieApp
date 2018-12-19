package itachi_waiyan.com.restapitest.service.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import itachi_waiyan.com.restapitest.service.model.SearchResult;
import itachi_waiyan.com.restapitest.service.model.TopRatedResult;
import itachi_waiyan.com.restapitest.service.model.MovieDetails;
import itachi_waiyan.com.restapitest.service.model.NowPlayingResult;
import itachi_waiyan.com.restapitest.service.model.PopularResult;
import itachi_waiyan.com.restapitest.service.model.TrailerUrls;
import itachi_waiyan.com.restapitest.service.model.UpcomingResult;
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

    public void callTopRatedResult(int page){
        Call<TopRatedResult>call = apiService.getDiscoverResult(Utils.API_KEY,page);
        call.enqueue(new Callback<TopRatedResult>() {
            @Override
            public void onResponse(Call<TopRatedResult> call, Response<TopRatedResult> response) {
                Log.d("response", String.valueOf(response.body().getTotal_pages()));
                BusProvider.getInstance().post(response.body());
            }

            @Override
            public void onFailure(Call<TopRatedResult> call, Throwable t) {

            }
        });
    }
    public void callPopularResult(int page){
        Call<PopularResult>call = apiService.getPopularResult(Utils.API_KEY,page);
        call.enqueue(new Callback<PopularResult>() {
            @Override
            public void onResponse(Call<PopularResult> call, Response<PopularResult> response) {
                BusProvider.getInstance().post(response.body());
            }

            @Override
            public void onFailure(Call<PopularResult> call, Throwable t) {

            }
        });
    }
    public void callNowPlayingResult(int page){
        Call<NowPlayingResult>call = apiService.getNowPlayingResult(Utils.API_KEY,page);
        call.enqueue(new Callback<NowPlayingResult>() {
            @Override
            public void onResponse(Call<NowPlayingResult> call, Response<NowPlayingResult> response) {
                BusProvider.getInstance().post(response.body());
            }

            @Override
            public void onFailure(Call<NowPlayingResult> call, Throwable t) {

            }
        });
    }
    public void callUpcomingResult(int page){
        Call<UpcomingResult>call = apiService.getUpcomingResult(Utils.API_KEY,page);
        call.enqueue(new Callback<UpcomingResult>() {
            @Override
            public void onResponse(Call<UpcomingResult> call, Response<UpcomingResult> response) {
                BusProvider.getInstance().post(response.body());
            }

            @Override
            public void onFailure(Call<UpcomingResult> call, Throwable t) {

            }
        });
    }
    public void callSearchResult(int page, String query, final Context context){
        Call<SearchResult>call = apiService.getSearchResult(Utils.API_KEY,page,query);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if(response.body()==null){
                    Toast.makeText(context,"Type somethings !",Toast.LENGTH_SHORT).show();
                }
                else BusProvider.getInstance().post(response.body());
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

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
