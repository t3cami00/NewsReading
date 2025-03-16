package com.example.newsreading.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InfoScreen(title: String, description: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Title: $title", modifier = Modifier.padding(bottom = 8.dp))
        Text(text = "Description: $description")
    }
}