package com.example.laba2.domain.api

import com.example.laba2.data.Track

interface TracksRepository {
    suspend fun searchTracksContains(expression: String): List<Track>
}
