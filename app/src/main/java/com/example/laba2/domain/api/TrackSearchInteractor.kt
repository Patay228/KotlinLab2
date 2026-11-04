package com.example.laba2.domain.api

import com.example.laba2.domain.model.Track

interface TrackSearchInteractor {
    suspend fun searchTracks(expression: String): List<Track>
}
