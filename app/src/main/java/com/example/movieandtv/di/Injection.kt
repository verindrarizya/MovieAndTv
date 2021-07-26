package com.example.movieandtv.di

import android.content.Context
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.data.source.local.LocalDataSource
import com.example.movieandtv.data.source.local.room.MovieTvDatabase
import com.example.movieandtv.data.source.remote.RemoteDataSource
import com.example.movieandtv.data.source.remote.api.TMDBClient

object Injection {

    fun provideRepository(context: Context): DataRepository {

        val database = MovieTvDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(TMDBClient.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao(), database.tvShowDao())

        return DataRepository.getInstance(remoteDataSource, localDataSource)
    }

}