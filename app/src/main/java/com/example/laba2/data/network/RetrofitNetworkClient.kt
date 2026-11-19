package com.example.laba2.data.network

import com.example.laba2.data.NetworkClient
import com.example.laba2.data.dto.BaseResponse

class RetrofitNetworkClient : NetworkClient {
    override suspend fun doRequest(dto: Any): BaseResponse {
        return BaseResponse().apply { resultCode = 200 }
    }
}
