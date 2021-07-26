package com.example.movieandtv.ui.favorite.favtvshowlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieandtv.data.source.local.entity.TvShowEntity
import com.example.movieandtv.databinding.FragmentTvShowListBinding
import com.example.movieandtv.factory.DataViewModelFactory
import com.example.movieandtv.ui.home.tvshowlist.TvShowListAdapter
import com.example.movieandtv.utils.setVisible


class FavTvShowListFragment : Fragment() {

    private lateinit var binding: FragmentTvShowListBinding
    private lateinit var viewModel: FavTvShowListViewModel

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
            viewModel = ViewModelProvider(this, factory)[FavTvShowListViewModel::class.java]

            viewModel.getFavTvShows().observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    binding.tvEmptyData.setVisible()
                } else {
                    initAdapter(it)
                }
            }
        }
    }

    private fun initAdapter(data: PagedList<TvShowEntity>) {
        val tvShowListAdapter = TvShowListAdapter()

        tvShowListAdapter.submitList(data)

        with(binding.rvTvShows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = tvShowListAdapter
        }
    }
}