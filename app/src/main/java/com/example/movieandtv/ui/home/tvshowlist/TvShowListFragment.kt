package com.example.movieandtv.ui.home.tvshowlist

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieandtv.R
import com.example.movieandtv.data.source.local.entity.TvShowEntity
import com.example.movieandtv.databinding.FragmentTvShowListBinding
import com.example.movieandtv.factory.DataViewModelFactory

import com.example.movieandtv.ui.tvshowdetail.TvShowDetailActivity
import com.example.movieandtv.utils.setGone
import com.example.movieandtv.utils.setVisible
import com.example.movieandtv.vo.Status

class TvShowListFragment : Fragment() {

    private lateinit var binding: FragmentTvShowListBinding
    private lateinit var tvShowListViewModel: TvShowListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTvShowListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = DataViewModelFactory.getInstance(requireActivity())
            tvShowListViewModel = ViewModelProvider(this, factory)[TvShowListViewModel::class.java]

            tvShowListViewModel.getTvShows().observe(viewLifecycleOwner) { tvShows ->
                if (tvShows != null) {
                    when(tvShows.status) {
                        Status.LOADING -> binding.progressBar.setVisible()

                        Status.SUCCESS -> {
                            binding.progressBar.setGone()
                            tvShows.data?.let { initAdapter(it) }
                        }

                        Status.ERROR -> {
                            binding.progressBar.setGone()
                            Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun initAdapter(data: PagedList<TvShowEntity>) {
        val tvShowListAdapter = TvShowListAdapter()

        tvShowListAdapter.submitList(data)
        tvShowListAdapter.setOnItemClickCallback(object: TvShowListAdapter.OnItemClickCallback {
            override fun onItemClicked(tvShow: TvShowEntity) {
                val intent = Intent(context, TvShowDetailActivity::class.java)
                intent.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW_ID, tvShow.id)
                startActivity(intent)
            }
        })

        with(binding.rvTvShows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = tvShowListAdapter
        }
    }
}