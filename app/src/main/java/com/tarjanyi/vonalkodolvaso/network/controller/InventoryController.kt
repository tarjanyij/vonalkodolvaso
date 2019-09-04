package com.tarjanyi.vonalkodolvaso.network.controller

import com.tarjanyi.vonalkodolvaso.model.InventoryData
import com.tarjanyi.vonalkodolvaso.network.api.InventoryApi
import com.tarjanyi.vonalkodolvaso.network.core.ApiClient
import com.tarjanyi.vonalkodolvaso.network.core.HttpClientFactory
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class InventoryController {


    fun getInventorys(baseUrlValue : String) : Single<InventoryData>{
        val client =ApiClient.makeHttpClient(HttpClientFactory().getClient(), baseUrlValue).create(InventoryApi::class.java)

        return client.getInventories()
            .subscribeOn(Schedulers.io())
    }
}