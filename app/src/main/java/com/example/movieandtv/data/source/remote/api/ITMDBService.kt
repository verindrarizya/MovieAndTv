package com.example.movieandtv.data.source.remote.api

import com.example.movieandtv.BuildConfig
import com.example.movieandtv.data.source.remote.response.ListResponses
import com.example.movieandtv.data.source.remote.response.MovieResponse
import com.example.movieandtv.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Query

interface ITMDBService {

    @GET("movie/top_rated")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<ListResponses<MovieResponse>>

    @GET("tv/top_rated")
    fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<ListResponses<TvShowResponse>>

}