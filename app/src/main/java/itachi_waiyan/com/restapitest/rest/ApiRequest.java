package itachi_waiyan.com.restapitest.rest;

import android.content.Context;
import android.util.Log;

import java.util.List;

import itachi_waiyan.com.restapitest.ApiResponse.DiscoverResult;
import itachi_waiyan.com.restapitest.App;
import itachi_waiyan.com.restapitest.room.AppDatabase;
import itachi_waiyan.com.restapitest.room.entity.DiscoverMovieEntity;
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
        Call<DiscoverResult>call = apiService.getDiscoverResult(Utils.API_KEY);
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
}
