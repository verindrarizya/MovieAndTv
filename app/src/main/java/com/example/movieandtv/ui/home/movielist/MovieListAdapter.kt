package com.example.movieandtv.ui.home.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.data.source.remote.api.TMDBAssets.BASE_IMAGE_URL
import com.example.movieandtv.databinding.ItemsPerRowBinding

class MovieListAdapter: PagedListAdapter<MovieEntity, MovieListAdapter.MovieListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    inner class MovieListViewHolder(private val binding: ItemsPerRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieEntity) {
            with(binding) {

                Glide.with(itemView.context)
                        .load("$BASE_IMAGE_URL${data.poster}")
                        .apply(RequestOptions().override(100, 150))
                        .into(imgPoster)

                tvTitle.text = data.title
                tvRating.text = data.rate.toString()
                tvDate.text = data.date

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(data) }
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = ItemsPerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(movie: MovieEntity)
    }

}