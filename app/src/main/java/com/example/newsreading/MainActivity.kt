package com.example.newsreading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.newsreading.data.api.NewsApiService
import com.example.newsreading.data.repository.NewsRepository
import com.example.newsreading.ui.navigation.AppNavigation
import com.example.newsreading.ui.viewmodel.NewsViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 创建 Retrofit 实例
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 创建 NewsApiService 实例
        val newsApiService = retrofit.create(NewsApiService::class.java)
        val repository = NewsRepository(newsApiService)
        val viewModel = NewsViewModel(repository)

        // 设置 Compose 内容
        setContent {
            AppNavigation(viewModel = viewModel)
        }
    }
}