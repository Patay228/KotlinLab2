package com.example.laba2.data

import com.example.laba2.data.dto.TrackDto

interface TracksStorage {
    fun getTracks(): List<TrackDto>
}

class TracksStorageImpl : TracksStorage {
    private val tracksDto = listOf(
        TrackDto("Yesterday (Remastered 2009)", "The Beatles", 125000),
        TrackDto("Here Comes The Sun (Remastered 2009)", "The Beatles", 187000),
        TrackDto("No Reply", "The Beatles", 152000),
        TrackDto("Let It Be", "The Beatles", 243000),
        TrackDto("Girl", "The Beatles", 138000),
        TrackDto("Michelle", "The Beatles", 167000),
        TrackDto("Eleanor Rigby", "The Beatles", 198000),
        TrackDto("Come Together", "The Beatles", 259000),
        TrackDto("Hey Jude (Remastered 2015)", "The Beatles", 431000),
        TrackDto("Something (Remastered 2009)", "The Beatles", 182000),
        TrackDto("Help! (Remastered 2009)", "The Beatles", 139000),
        TrackDto("Yellow Submarine (Remastered 2009)", "The Beatles", 158000),
        TrackDto("In My Life (Remastered 2009)", "The Beatles", 146000),
        TrackDto("All You Need Is Love (Remastered 2009)", "The Beatles", 227000)
    )

    override fun getTracks(): List<TrackDto> = tracksDto
}
