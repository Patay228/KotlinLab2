package com.example.laba2.data.network

import com.example.laba2.data.NetworkClient
import com.example.laba2.data.dto.TrackDto
import com.example.laba2.domain.api.TracksRepository
import com.example.laba2.domain.model.Track
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Locale

class TracksRepositoryImpl(
    private val networkClient: NetworkClient
) : TracksRepository {
    private val tracksDto = listOf(
        TrackDto("Yesterday (Remastered 2009)", "The Beatles", 125000),
        TrackDto("Here Comes The Sun (Remastered 2009)", "The Beatles", 185000),
        TrackDto("Come Together (Remastered 2009)", "The Beatles", 259000),
        TrackDto("Let It Be (Remastered 2009)", "The Beatles", 243000),
        TrackDto("Hey Jude (Remastered 2015)", "The Beatles", 431000),
        TrackDto("Something (Remastered 2009)", "The Beatles", 182000),
        TrackDto("Help! (Remastered 2009)", "The Beatles", 139000),
        TrackDto("Yellow Submarine (Remastered 2009)", "The Beatles", 158000),
        TrackDto("In My Life (Remastered 2009)", "The Beatles", 146000),
        TrackDto("All You Need Is Love (Remastered 2009)", "The Beatles", 227000)
    )

    override suspend fun searchTracksContains(expression: String): List<Track> {
        delay(3000)
        return tracksDto
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
