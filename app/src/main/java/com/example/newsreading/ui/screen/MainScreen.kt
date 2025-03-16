package com.example.newsreading.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsreading.ui.viewmodel.NewsViewModel

@Composable
fun MainScreen(viewModel: NewsViewModel, navController: NavController) {
    val newsState = viewModel.newsState.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {
        when (newsState) {
            is NewsViewModel.NewsState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is NewsViewModel.NewsState.Success -> {
                val articles = (newsState ).newsResponse.articles
                LazyColumn {
                    items(articles) { article ->
                        Text(
                            text = article.title,
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    // 导航到 InfoScreen，并传递新闻数据
                                    navController.navigate("info/${article.title}/${article.description}")
                                }
                        )
                    }
                }
            }
            is NewsViewModel.NewsState.Error -> {
                Text(text = (newsState ).message, modifier = Modifier.padding(16.dp))
            }
        }
    }
}