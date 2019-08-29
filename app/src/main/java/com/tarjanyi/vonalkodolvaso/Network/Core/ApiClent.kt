package com.tarjanyi.vonalkodolvaso.Network.Core

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    fun makeHttpClient(httpClient:OkHttpClient, baseUrl:String) : Retrofit {
        val moshi =Moshi.Builder().add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }
}