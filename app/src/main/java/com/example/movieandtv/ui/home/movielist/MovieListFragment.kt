package com.example.movieandtv.ui.home.movielist

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
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.databinding.FragmentMovieListBinding
import com.example.movieandtv.factory.DataViewModelFactory
import com.example.movieandtv.ui.moviedetail.MovieDetailActivity
import com.example.movieandtv.utils.setGone
import com.example.movieandtv.utils.setVisible
import com.example.movieandtv.vo.Status

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieListViewModel: MovieListViewModel

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
            movieListViewModel = ViewModelProvider(this, factory)[MovieListViewModel::class.java]

            movieListViewModel.getMovies().observe(viewLifecycleOwner) { movies ->
                if (movies != null) {
                    when(movies.status) {
                        Status.LOADING -> binding.progressBar.setVisible()

                        Status.SUCCESS -> {
                            binding.progressBar.setGone()
                            movies.data?.let {
                                initAdapter(it)
                            }
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

    private fun initAdapter(data: PagedList<MovieEntity>) {
        val movieListAdapter = MovieListAdapter()

        movieListAdapter.submitList(data)
        movieListAdapter.setOnItemClickCallback(object: MovieListAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: MovieEntity) {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movie.id)
                startActivity(intent)
            }
        })

        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = movieListAdapter
        }
    }
}