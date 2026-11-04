package com.example.laba2.data.di

import com.example.laba2.data.NetworkClient
import com.example.laba2.data.network.RetrofitNetworkClient
import com.example.laba2.data.network.TracksRepositoryImpl
import com.example.laba2.domain.api.TracksRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<NetworkClient> {
        RetrofitNetworkClient()
    }
    factory<TracksRepository> {
        TracksRepositoryImpl(get())
    }
}
