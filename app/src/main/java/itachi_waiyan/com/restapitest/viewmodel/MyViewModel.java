package itachi_waiyan.com.restapitest.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import itachi_waiyan.com.restapitest.room.AppDatabase;

public class MyViewModel extends ViewModel {
    private ViewModel viewModel;
    private AppDatabase appDatabase;


    public ViewModel getInstance(Context context){
        if(viewModel==null){
            viewModel = new MyViewModel();
            appDatabase = AppDatabase.getAppDatabase(context);
        }
        return viewModel;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
