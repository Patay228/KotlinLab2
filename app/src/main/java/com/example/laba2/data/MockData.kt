package com.example.laba2.data

object MockData {
    private fun art(seed: Int) = "https://dummyimage.com/200x200/3772E7/FFFFFF.png&text=Cover"

    val tracks: List<Track> = listOf(
        Track(1, "Yesterday (Remastered 2009)", "The Beatles", 175_000, art(1)),
        Track(2, "Here Comes The Sun (Remastered 2009)", "The Beatles", 241_000, art(2)),
        Track(3, "No Reply", "The Beatles", 312_000, art(3)),
        Track(4, "Let It Be", "The Beatles", 361_000, art(4)),
        Track(5, "Girl", "The Beatles", 251_000, art(5)),
        Track(6, "Michelle", "The Beatles", 181_000, art(6)),
        Track(7, "Eleanor Rigby", "The Beatles", 372_000, art(7)),
        Track(8, "Come Together", "The Beatles", 249_000, art(8)),
        Track(9, "Help!", "The Beatles", 232_000, art(9))
    )

    val playlists: List<Playlist> = listOf(
        Playlist(1, "Best songs 2021", "Мои любимые песни", art(101), 98),
        Playlist(2, "Summer Party", "Летнее настроение", art(102), 157),
        Playlist(3, "Morning", "Утренний плейлист", art(103), 32)
    )

    val searchHistory: MutableList<Track> = mutableListOf(
        tracks[0], tracks[1], tracks[2], tracks[3], tracks[4]
    )

    val favorites: MutableList<Track> = mutableListOf()
}