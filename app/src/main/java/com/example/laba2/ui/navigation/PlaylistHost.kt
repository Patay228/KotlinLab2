package com.example.laba2.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.laba2.domain.model.Track
import com.example.laba2.presentation.SearchViewModel
import com.example.laba2.ui.main.MainScreen
import com.example.laba2.ui.search.DetailsScreen
import com.example.laba2.ui.search.SearchScreen
import com.example.laba2.ui.setting.SettingScreen
import com.google.gson.Gson

@Composable
fun PlaylistHost(
    modifier: Modifier = Modifier,
    startDestination: String = "main",
    navController: NavHostController,
    gson: Gson,
    searchViewModel: SearchViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = "main") {
            MainScreen(
                onSearchClick = { navController.navigate("search") },
                onSettingsClick = { navController.navigate("setting") }
            )
        }

        composable(route = "search") {
            SearchScreen(
                searchViewModel = searchViewModel,
                navigateToDetailScreen = { track ->
                    val trackJson = Uri.encode(gson.toJson(track))
                    navController.navigate("details/$trackJson")
                },
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "details/{trackJson}"
        ) { backStackEntry: NavBackStackEntry ->
            val trackJson = backStackEntry.arguments?.getString("trackJson")
            if (trackJson != null) {
                val decodedJson = Uri.decode(trackJson)
                val track = gson.fromJson(decodedJson, Track::class.java)
                DetailsScreen(
                    track = track,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        composable(route = "setting") {
            SettingScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
