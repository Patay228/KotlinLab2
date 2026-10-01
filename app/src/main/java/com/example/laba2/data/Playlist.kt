package com.example.laba2.data

data class Playlist(
    val id: Long,
    val name: String,
    val description: String,
    val coverUrl: String,
    val tracksCount: Int
)