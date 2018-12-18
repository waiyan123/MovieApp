package itachi_waiyan.com.restapitest;

import android.app.Application;

import itachi_waiyan.com.restapitest.room.AppDatabase;
import itachi_waiyan.com.restapitest.utils.BusProvider;

public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        BusProvider.getInstance().register(this);
    }

    public static synchronized App getInstance(){

        return mInstance;
    }

    public AppDatabase getDatabase(){
        return AppDatabase.getAppDatabase(this);
    }

    @Override
    public void onTerminate() {
        BusProvider.getInstance().unregister(this);
        super.onTerminate();
    }
}
