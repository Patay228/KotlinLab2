package com.example.laba2.data.network

import com.example.laba2.data.NetworkClient
import com.example.laba2.data.TracksStorage
import com.example.laba2.data.TracksStorageImpl
import com.example.laba2.domain.api.TracksRepository
import com.example.laba2.domain.model.Track
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Locale

class TracksRepositoryImpl(
    private val networkClient: NetworkClient? = null,
    private val tracksStorage: TracksStorage = TracksStorageImpl()
) : TracksRepository {

    override suspend fun searchTracksContains(expression: String): List<Track> {
        delay(2000)
        return tracksStorage.getTracks()
            .filter { it.trackName.contains(expression, ignoreCase = true) }
            .map {
                Track(
                    trackName = it.trackName,
                    artistName = it.artistName,
                    trackTime = SimpleDateFormat("mm:ss", Locale.getDefault()).format(it.trackTimeMillis),
                    artworkUrl100 = it.artworkUrl100
                )
            }
    }
}
