package com.example.movieandtv.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movieandtv.data.source.local.LocalDataSource
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.data.source.local.entity.TvShowEntity
import com.example.movieandtv.data.source.remote.RemoteDataSource
import com.example.movieandtv.data.source.remote.response.MovieResponse
import com.example.movieandtv.data.source.remote.response.TvShowResponse
import com.example.movieandtv.data.source.remote.responsewrapper.ApiResponse
import com.example.movieandtv.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ): IDataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): DataRepository {
            return instance ?: synchronized(this) {
                instance ?: DataRepository(remoteDataSource, localDataSource).apply {
                    instance = this
                }
            }
        }
    }

    // Movies
    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>() {

            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> = remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                data.forEach {
                    val movie = MovieEntity(
                        it.id,
                        it.title,
                        it.posterPath,
                        it.releaseDate,
                        it.voteAverage,
                        it.overview,
                        isFavorite = false
                    )

                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getMovie(movieId: Int): LiveData<MovieEntity> = localDataSource.getMovie(movieId)

    override fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.makeMovieFavorite(movie, newState)
        }
    }

    //Tv Shows
    override fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>() {

            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> = remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()

                data.forEach {
                    val tvShow = TvShowEntity(
                        it.id,
                        it.name,
                        it.posterPath,
                        it.firstAirDate,
                        it.voteAverage,
                        it.overview,
                        isFavorite = false
                    )

                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShows(tvShowList)
            }

        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>  {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun getTvShow(tvShowId: Int): LiveData<TvShowEntity> = localDataSource.getTvShow(tvShowId)

    override fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.makeTvShowFavorite(tvShow, newState)
        }
    }

}