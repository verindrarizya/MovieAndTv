package com.example.movieandtv.ui.moviedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieandtv.R
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.data.source.remote.api.TMDBAssets.BASE_IMAGE_URL
import com.example.movieandtv.databinding.ActivityMovieDetailBinding
import com.example.movieandtv.databinding.ContentMovieDetailBinding
import com.example.movieandtv.factory.DataViewModelFactory
import com.example.movieandtv.utils.setGone
import com.example.movieandtv.utils.setVisible

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var detailContentMovie: ContentMovieDetailBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        detailContentMovie = binding.contentMovieDetail
        setContentView(binding.root)

        initActionBar()

        val factory = DataViewModelFactory.getInstance(this)
        movieDetailViewModel = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]

        val bundle = intent.extras
        if (bundle != null) {
            val movieId = bundle.getInt(EXTRA_MOVIE_ID)

            movieDetailViewModel.setMovieId(movieId)

            binding.progressBar.setVisible()

            movieDetailViewModel.getMovieDetail().observe(this, { movie ->
                binding.progressBar.setGone()
                binding.content.setVisible()

                populateContentMovieDetail(movie)
                initFabMovie(movie)

                binding.fabFavorite.setOnClickListener { movieDetailViewModel.setMovieFavorite(movie) }
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun populateContentMovieDetail(data: MovieEntity) {
        with(detailContentMovie) {

            Glide.with(this@MovieDetailActivity)
                .load("$BASE_IMAGE_URL${data.poster}")
                .apply(RequestOptions().override(120, 170))
                .into(imgPoster)

            tvTitle.text = data.title
            tvReleaseDate.text = data.date
            tvRating.text = data.rate.toString()
            tvOverview.text = data.overview

        }
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.movie_detail_page_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initFabMovie(movieEntity: MovieEntity) {
        if (movieEntity.isFavorite) {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_filled_24)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_outline_24)
        }
    }
}