package com.example.movieandtv.ui.favorite.favmovielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.databinding.FragmentMovieListBinding
import com.example.movieandtv.factory.DataViewModelFactory
import com.example.movieandtv.ui.home.movielist.MovieListAdapter
import com.example.movieandtv.utils.setVisible


class FavMovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: FavMovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = DataViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavMovieListViewModel::class.java]

            viewModel.getFavMovies().observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    binding.tvEmptyData.setVisible()
                } else {
                    initAdapter(it)
                }
            }
        }
    }

    private fun initAdapter(data: PagedList<MovieEntity>) {
        val movieListAdapter = MovieListAdapter()

        movieListAdapter.submitList(data)

        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = movieListAdapter
        }
    }

}