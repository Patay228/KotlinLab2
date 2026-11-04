package com.example.laba2.domain.model

import java.text.SimpleDateFormat
import java.util.Locale

data class Track(
    val trackId: Long = 0L,
    val trackName: String = "",
    val artistName: String = "",
    val trackTime: String = "",
    val artworkUrl100: String? = null,
    val trackTimeMillis: Long = 0L,
    val id: Long = trackId,
    val artworkUrl: String = artworkUrl100 ?: "",
    val collectionName: String = "",
    val releaseDate: String = "",
    val primaryGenreName: String = "",
    val country: String = "",
    val previewUrl: String = ""
) {
    constructor(
        id: Long,
        trackName: String,
        artistName: String,
        trackTimeMillis: Long,
        artworkUrl: String,
        collectionName: String = "",
        releaseDate: String = "",
        primaryGenreName: String = "",
        country: String = "",
        previewUrl: String = ""
    ) : this(
        trackId = id,
        trackName = trackName,
        artistName = artistName,
        trackTime = SimpleDateFormat("mm:ss", Locale.getDefault()).format(trackTimeMillis),
        artworkUrl100 = artworkUrl,
        trackTimeMillis = trackTimeMillis,
        id = id,
        artworkUrl = artworkUrl,
        collectionName = collectionName,
        releaseDate = releaseDate,
        primaryGenreName = primaryGenreName,
        country = country,
        previewUrl = previewUrl
    )

    constructor(
        trackName: String,
        artistName: String,
        trackTime: String,
        artworkUrl100: String? = null
    ) : this(
        trackId = 0L,
        trackName = trackName,
        artistName = artistName,
        trackTime = trackTime,
        artworkUrl100 = artworkUrl100,
        trackTimeMillis = 0L,
        id = 0L,
        artworkUrl = artworkUrl100 ?: "",
        collectionName = "",
        releaseDate = "",
        primaryGenreName = "",
        country = "",
        previewUrl = ""
    )
}
