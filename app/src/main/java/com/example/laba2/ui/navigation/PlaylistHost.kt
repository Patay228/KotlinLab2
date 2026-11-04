package com.example.laba2.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.laba2.data.Track
import com.example.laba2.ui.screens.DetailsScreen
import com.example.laba2.ui.screens.MainScreen
import com.example.laba2.ui.screens.SearchScreen
import com.example.laba2.ui.screens.SettingsScreen
import com.example.laba2.ui.view_model.SearchViewModel
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
            SettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
