package com.example.laba2.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.laba2.Creator
import com.example.laba2.domain.api.TrackSearchInteractor
import com.example.laba2.domain.api.TracksRepository
import com.example.laba2.domain.impl.TrackSearchInteractorImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val tracksInteractor: TrackSearchInteractor = Creator.provideTracksInteractor()
) : ViewModel() {

    constructor(tracksRepository: TracksRepository) : this(
        TrackSearchInteractorImpl(tracksRepository)
    )

    private val _searchScreenState = MutableStateFlow<SearchState>(SearchState.Initial)
    val searchScreenState = _searchScreenState.asStateFlow()

    fun searchTracks(query: String) {
        if (query.isBlank()) {
            _searchScreenState.value = SearchState.Initial
            return
        }
        viewModelScope.launch {
            _searchScreenState.value = SearchState.Searching
            try {
                val result = withContext(Dispatchers.IO) {
                    tracksInteractor.searchTracks(query)
                }
                if (result.isEmpty()) {
                    _searchScreenState.value = SearchState.Fail("Ничего не нашлось")
                } else {
                    _searchScreenState.value = SearchState.Success(result)
                }
            } catch (e: Exception) {
                _searchScreenState.value = SearchState.Fail(e.message ?: "Проблемы со связью")
            }
        }
    }

    fun clearSearch() {
        _searchScreenState.value = SearchState.Initial
    }

    companion object {
        fun getViewModelFactory(context: Context? = null): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SearchViewModel(Creator.provideTracksInteractor()) as T
                }
            }

        fun getViewModelFactory(): ViewModelProvider.Factory = getViewModelFactory(null)
    }
}
