package com.example.laba2.data

data class Track(
    val id: Long,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Long = 0L,
    val artworkUrl: String = "",
    val trackTime: String = if (trackTimeMillis > 0) {
        val seconds = trackTimeMillis / 1000
        val minutes = seconds / 60
        "%02d:%02d".format(minutes, seconds % 60)
    } else "",
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
        trackTime: String
    ) : this(
        id = id,
        trackName = trackName,
        artistName = artistName,
        trackTimeMillis = 0L,
        artworkUrl = "",
        trackTime = trackTime
    )
}
