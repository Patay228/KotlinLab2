package com.example.laba2.ui.navigation

import  androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laba2.ui.screens.MainScreen
import com.example.laba2.ui.screens.SearchScreen
import com.example.laba2.ui.screens.SettingsScreen

@Composable
fun PlaylistHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    fun navigateToSearch() {
        navController.navigate(PlaylistScreen.SEARCH.name)
    }

    fun navigateToSettings() {
        navController.navigate(PlaylistScreen.SETTINGS.name)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    NavHost(
        navController = navController,
        startDestination = PlaylistScreen.MAIN.name,
        modifier = modifier
    ) {
        composable(PlaylistScreen.MAIN.name) {
            MainScreen(
                onSearchClick = { navigateToSearch() },
                onSettingsClick = { navigateToSettings() },
                onMediaClick = { /* Зарезервировано для экрана медиатеки */ }
            )
        }
        composable(PlaylistScreen.SEARCH.name) {
            SearchScreen(
                onBackClick = { navigateBack() }
            )
        }
        composable(PlaylistScreen.SETTINGS.name) {
            SettingsScreen(
                onBackClick = { navigateBack() }
            )
        }
    }
}
