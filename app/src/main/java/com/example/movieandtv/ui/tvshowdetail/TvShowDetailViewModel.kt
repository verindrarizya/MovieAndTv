package com.example.movieandtv.ui.tvshowdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.data.source.local.entity.TvShowEntity

class TvShowDetailViewModel(private val mDataRepository: DataRepository): ViewModel() {

    private var tvShowId: Int = 0

    fun setTvShowId(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getTvShowDetail(): LiveData<TvShowEntity> = mDataRepository.getTvShow(tvShowId)

    fun setTvShowFavorite(tvShowEntity: TvShowEntity) {
        mDataRepository.setFavoriteTvShow(tvShowEntity, !tvShowEntity.isFavorite)
    }

}