package itachi_waiyan.com.restapitest.room;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import itachi_waiyan.com.restapitest.room.dao.DiscoverMovieDao;
import itachi_waiyan.com.restapitest.room.entity.DiscoverMovieEntity;

@Database (entities = {DiscoverMovieEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    public abstract DiscoverMovieDao discoverMovieDao();
    static AppDatabase instance;
    static final String DB_NAME = "MyRoom";
    public static AppDatabase getAppDatabase(final Context context) {
        if(instance==null){
            instance = Room.databaseBuilder(context,AppDatabase.class,DB_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
}
