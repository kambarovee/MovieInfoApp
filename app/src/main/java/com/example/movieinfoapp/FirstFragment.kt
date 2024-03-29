package com.example.movieinfoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieinfoapp.adapter.MoviesAdapter
import com.example.movieinfoapp.databinding.FragmentFirstBinding
import com.example.movieinfoapp.model.Movie

class FirstFragment : Fragment() {

    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager
    private lateinit var viewModel: FirstFragmentViewModel
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first,
            container,
            false
        )

        binding.setLifecycleOwner(this)

        viewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)

        val progress = binding.progressMovies
        val popularMovies = binding.popularMovies

        popularMoviesLayoutMgr = LinearLayoutManager(this.activity)
        popularMovies.layoutManager = popularMoviesLayoutMgr
        popularMoviesAdapter = MoviesAdapter(mutableListOf())
        popularMovies.adapter = popularMoviesAdapter

        viewModel.getMovie().observe(
            viewLifecycleOwner,
            Observer { newMovies ->
                onPopularMoviesFetched(newMovies)
                progress.visibility = View.GONE
            }
        )

        return binding.root
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.appendMovies(movies)
    }
}
