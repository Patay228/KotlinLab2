package com.example.laba2.data

import com.example.laba2.data.dto.BaseResponse

interface NetworkClient {
    suspend fun doRequest(dto: Any): BaseResponse = BaseResponse().apply { resultCode = 200 }
}
