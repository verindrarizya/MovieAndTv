package com.example.movieandtv.data.source.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.movieandtv.data.source.local.entity.TvShowEntity

@Dao
interface ITvShowDao {

    @Query("SELECT * FROM tv_show")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_show WHERE is_favorite = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_show WHERE id = :tvShowId")
    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(movies: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

}