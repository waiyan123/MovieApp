package itachi_waiyan.com.restapitest.service.repository;

import android.content.Context;

import itachi_waiyan.com.restapitest.room.AppDatabase;
import itachi_waiyan.com.restapitest.utils.Utils;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectRepo {

    private ApiInterface apiInterface;
    private static ProjectRepo projectRepo;

    private ProjectRepo(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);


    }

    public synchronized static ProjectRepo getInstance(){
        if(projectRepo==null){
            projectRepo = new ProjectRepo();
        }
        return projectRepo;
    }

    public ApiInterface getApiInterface(){
        return apiInterface;
    }

}
