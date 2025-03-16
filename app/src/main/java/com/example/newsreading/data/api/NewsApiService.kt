package com.example.newsreading.data.api

import com.example.newsreading.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "d401bde71e694aa099a467d1e4c4497d"
    ): Response<NewsResponse>
}