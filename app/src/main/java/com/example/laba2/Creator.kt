package com.example.laba2

import com.example.laba2.data.NetworkClient
import com.example.laba2.data.network.RetrofitNetworkClient
import com.example.laba2.data.network.TracksRepositoryImpl
import com.example.laba2.domain.api.TrackSearchInteractor
import com.example.laba2.domain.api.TracksRepository
import com.example.laba2.domain.impl.TrackSearchInteractorImpl

object Creator {
    private fun getNetworkClient(): NetworkClient {
        return RetrofitNetworkClient()
    }

    fun getTracksRepository(): TracksRepository {
        return TracksRepositoryImpl(getNetworkClient())
    }

    fun provideTracksInteractor(): TrackSearchInteractor {
        return TrackSearchInteractorImpl(getTracksRepository())
    }
}
