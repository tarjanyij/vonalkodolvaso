package com.tarjanyi.vonalkodolvaso.Network.Core

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class HttpClientFactory {
    fun getClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(5,TimeUnit.SECONDS)

            .build()
    }
}