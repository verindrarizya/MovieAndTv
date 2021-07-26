package com.example.movieandtv.ui.tvshowdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieandtv.R
import com.example.movieandtv.data.source.local.entity.TvShowEntity
import com.example.movieandtv.data.source.remote.api.TMDBAssets
import com.example.movieandtv.databinding.ActivityTvShowDetailBinding
import com.example.movieandtv.databinding.ContentTvShowDetailBinding
import com.example.movieandtv.factory.DataViewModelFactory
import com.example.movieandtv.utils.setGone
import com.example.movieandtv.utils.setVisible

class TvShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW_ID = "extra_tv_show_id"
    }

    private lateinit var binding: ActivityTvShowDetailBinding
    private lateinit var detailContentTvShow: ContentTvShowDetailBinding
    private lateinit var tvShowDetailViewModel: TvShowDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        detailContentTvShow = binding.contentMovieDetail
        setContentView(binding.root)

        initActionBar()

        val factory = DataViewModelFactory.getInstance(this)
        tvShowDetailViewModel = ViewModelProvider(this, factory)[TvShowDetailViewModel::class.java]

        val bundle = intent.extras
        if (bundle != null) {
            val tvShowId = bundle.getInt(EXTRA_TV_SHOW_ID)

            tvShowDetailViewModel.setTvShowId(tvShowId)

            binding.progressBar.setVisible()

            tvShowDetailViewModel.getTvShowDetail().observe(this, { tvShow ->
                binding.progressBar.setGone()
                binding.content.setVisible()

                populateContentTvShow(tvShow)
                initFabTvShow(tvShow)

                binding.fabFavorite.setOnClickListener { tvShowDetailViewModel.setTvShowFavorite(tvShow) }
            })
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun populateContentTvShow(data: TvShowEntity) {
        with(detailContentTvShow) {

            Glide.with(this@TvShowDetailActivity)
                .load("${TMDBAssets.BASE_IMAGE_URL}${data.poster}")
                .apply(RequestOptions().override(120, 170))
                .into(imgPoster)

            tvTitle.text = data.title
            tvFirstAirDate.text = data.date
            tvRating.text = data.rate.toString()
            tvOverview.text = data.overview

        }
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.tv_show_detail_page_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initFabTvShow(tvShowEntity: TvShowEntity) {
        if (tvShowEntity.isFavorite) {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_filled_24)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_outline_24)
        }
    }

}