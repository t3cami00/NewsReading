package com.example.newsreading.data.repository

import com.example.newsreading.data.api.NewsApiService
import com.example.newsreading.data.model.NewsResponse
import retrofit2.Response

class NewsRepository(private val newsApiService: NewsApiService) {
    suspend fun getTopHeadlines(): Response<NewsResponse> {
        return newsApiService.getTopHeadlines()
    }
}