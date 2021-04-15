package com.example.movieinfoapp.retrofit

import com.example.movieinfoapp.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MoviesResponse
}
