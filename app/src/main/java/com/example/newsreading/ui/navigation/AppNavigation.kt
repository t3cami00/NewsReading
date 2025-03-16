package com.example.newsreading.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsreading.ui.screen.InfoScreen
import com.example.newsreading.ui.screen.MainScreen
import com.example.newsreading.ui.viewmodel.NewsViewModel

@Composable
fun AppNavigation(viewModel: NewsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = "info/{title}/{description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            InfoScreen(title = title, description = description)
        }
    }
}