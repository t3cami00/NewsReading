package com.example.newsreading.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreading.data.model.NewsResponse
import com.example.newsreading.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _newsState = MutableStateFlow<NewsState>(NewsState.Loading)
    val newsState: StateFlow<NewsState> = _newsState

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines()
                if (response.isSuccessful) {
                    _newsState.value = NewsState.Success(response.body()!!)
                } else {
                    _newsState.value = NewsState.Error("Failed to load news: ${response.message()}")
                }
            } catch (e: Exception) {
                _newsState.value = NewsState.Error(e.message ?: "An error occurred")
            }
        }
    }

    sealed class NewsState {
        data object Loading : NewsState()
        data class Success(val newsResponse: NewsResponse) : NewsState()
        data class Error(val message: String) : NewsState()
    }
}