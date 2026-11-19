package com.example.laba2.domain.impl

import com.example.laba2.domain.api.TrackSearchInteractor
import com.example.laba2.domain.api.TracksRepository
import com.example.laba2.domain.model.Track

class TrackSearchInteractorImpl(
    private val repository: TracksRepository
) : TrackSearchInteractor {
    override suspend fun searchTracks(expression: String): List<Track> {
        return repository.searchTracksContains(expression)
    }
}
