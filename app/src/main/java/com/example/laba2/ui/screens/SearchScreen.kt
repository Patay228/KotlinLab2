package com.example.laba2.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laba2.domain.model.Track
import com.example.laba2.presentation.SearchViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(factory = SearchViewModel.getViewModelFactory()),
    onBackClick: (() -> Unit)? = null,
    navigateToDetailScreen: (Track) -> Unit = {}
) {
    com.example.laba2.ui.search.SearchScreen(
        modifier = modifier,
        viewModel = viewModel,
        onBackClick = onBackClick,
        navigateToDetailScreen = navigateToDetailScreen
    )
}

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    navigateToDetailScreen: (Track) -> Unit = {},
    navigateBack: () -> Unit = {}
) {
    com.example.laba2.ui.search.SearchScreen(
        searchViewModel = searchViewModel,
        navigateToDetailScreen = navigateToDetailScreen,
        navigateBack = navigateBack
    )
}
