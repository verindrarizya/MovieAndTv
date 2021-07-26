package com.example.movieandtv.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieandtv.data.source.remote.api.ITMDBService
import com.example.movieandtv.data.source.remote.response.MovieResponse
import com.example.movieandtv.data.source.remote.response.TvShowResponse
import com.example.movieandtv.data.source.remote.responsewrapper.ApiResponse
import com.example.movieandtv.utils.EspressoIdlingResources
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class RemoteDataSource private constructor(private val ITMDBService: ITMDBService) {

    companion object {
        private const val EMPTY_RESULT = "Empty Result"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(ITMDBService: ITMDBService): RemoteDataSource {
            return instance ?: synchronized(this) {
                instance ?: RemoteDataSource(ITMDBService).apply {
                    instance = this
                }
            }
        }
    }

    private val remoteFetchScope = CoroutineScope(Dispatchers.IO + CoroutineName("RemoteFetchWorker"))

    fun getMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResources.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        remoteFetchScope.launch {
            try {
                ITMDBService.getMovies().await().results.let {
                    if (it != null) {
                        resultMovies.postValue(ApiResponse.success(it))
                    } else {
                        resultMovies.postValue(ApiResponse.empty(EMPTY_RESULT, it))
                    }
                }
            } catch (e: Exception) {
                resultMovies.postValue(ApiResponse.error(e.toString(), null))
            }
        }

        EspressoIdlingResources.decrement()

        return resultMovies
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResources.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<TvShowResponse>>>()

        remoteFetchScope.launch {
            try {
                ITMDBService.getTvShows().await().results.let {
                    if (it != null) {
                        resultTvShows.postValue(ApiResponse.success(it))
                    } else {
                        resultTvShows.postValue(ApiResponse.empty(EMPTY_RESULT,it))
                    }
                }
            } catch (e: Exception) {
                resultTvShows.postValue(ApiResponse.error(e.toString(), null))
            }
        }

        EspressoIdlingResources.decrement()

        return resultTvShows
    }

}