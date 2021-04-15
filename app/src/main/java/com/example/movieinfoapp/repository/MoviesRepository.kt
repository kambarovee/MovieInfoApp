package com.example.movieinfoapp.repository

import com.example.movieinfoapp.retrofit.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException

object MoviesRepository {
    private val api: Api
    private val apiKey: String = "0190a4b51699a244b640d31adee94266"

    init {
        var client = OkHttpClient()
        try {
            client = OkHttpClient.Builder()
                .build()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

    suspend fun getPopularMovies() = api.getPopularMovies(apiKey = apiKey, page = 1)
}
