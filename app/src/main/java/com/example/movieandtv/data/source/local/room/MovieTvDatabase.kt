package com.example.movieandtv.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.data.source.local.entity.TvShowEntity
import com.example.movieandtv.data.source.local.room.dao.IMovieDao
import com.example.movieandtv.data.source.local.room.dao.ITvShowDao

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieTvDatabase: RoomDatabase() {

    abstract fun movieDao(): IMovieDao

    abstract fun tvShowDao(): ITvShowDao

    companion object {

        @Volatile
        private var instance: MovieTvDatabase? = null

        fun getInstance(context: Context): MovieTvDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, MovieTvDatabase::class.java, "movie_tv.db")
                    .build()
                    .apply {
                        instance = this
                    }
            }
        }

    }

}