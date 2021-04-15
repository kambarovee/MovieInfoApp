package com.example.movieinfoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movieinfoapp.model.Movie
import com.example.movieinfoapp.repository.MoviesRepository
import kotlinx.coroutines.*

class FirstFragmentViewModel : ViewModel() {

    private val movies = liveData(Dispatchers.IO) {
        val popularMovie = MoviesRepository.getPopularMovies().movies
        emit(popularMovie)
    }

    fun getMovie(): LiveData<List<Movie>> {
        return movies
    }
}
