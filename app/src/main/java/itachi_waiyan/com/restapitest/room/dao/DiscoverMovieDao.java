package itachi_waiyan.com.restapitest.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import itachi_waiyan.com.restapitest.room.entity.DiscoverMovieEntity;

@Dao
public interface DiscoverMovieDao {

    @Query("SELECT * FROM discover_movie_table")
    List<DiscoverMovieEntity>getMovieList();

    @Insert
    void insertMovie(DiscoverMovieEntity discoverMovieEntity);

    @Query("SELECT * FROM discover_movie_table WHERE video_id = :videoId")
    DiscoverMovieEntity fetchByVideoId(int videoId);


}
