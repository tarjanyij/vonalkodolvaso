package com.tarjanyi.vonalkodolvaso.network.api

import com.tarjanyi.vonalkodolvaso.model.InventoryData
import io.reactivex.Single
import retrofit2.http.GET

interface InventoryApi {

    @GET(".")
    fun getInventories() : Single<InventoryData>
}