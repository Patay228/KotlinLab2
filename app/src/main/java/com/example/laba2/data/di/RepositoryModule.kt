package com.example.laba2.data.di

import com.example.laba2.data.NetworkClient
import com.example.laba2.data.network.RetrofitNetworkClient
import com.example.laba2.data.network.TracksRepositoryImpl
import com.example.laba2.domain.api.TrackSearchInteractor
import com.example.laba2.domain.api.TracksRepository
import com.example.laba2.domain.impl.TrackSearchInteractorImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<NetworkClient> { RetrofitNetworkClient() }
    factory<TracksRepository> { TracksRepositoryImpl(get()) }
    factory<TrackSearchInteractor> { TrackSearchInteractorImpl(get()) }
}
