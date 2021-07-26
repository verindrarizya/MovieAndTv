package com.example.movieandtv.ui.home.tvshowlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieandtv.data.source.local.entity.TvShowEntity
import com.example.movieandtv.data.source.remote.api.TMDBAssets
import com.example.movieandtv.databinding.ItemsPerRowBinding

class TvShowListAdapter: PagedListAdapter<TvShowEntity, TvShowListAdapter.TvShowListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    inner class TvShowListViewHolder(private val binding: ItemsPerRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvShowEntity) {
            with(binding) {

                Glide.with(itemView.context)
                        .load("${TMDBAssets.BASE_IMAGE_URL}${data.poster}")
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowListViewHolder {
        val binding = ItemsPerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowListViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(tvShow: TvShowEntity)
    }
}