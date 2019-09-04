package com.tarjanyi.vonalkodolvaso

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tarjanyi.vonalkodolvaso.network.controller.InventoryController
import io.reactivex.android.schedulers.AndroidSchedulers


class DataPublisher : AppCompatActivity() {

    private var deviceNameTV : TextView? = null
    private var serialNumberTV : TextView? = null
    private var placeTV : TextView? = null
    private var barcodeTV : TextView? = null
    private var nameTV : TextView? = null
    private var btnNewScan : Button? =null

    companion object {
        const val BASE_URL = "http://10.4.3.101:8080/api/inventory/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_publisher)

        deviceNameTV = findViewById(R.id.deviceNameTV)
        serialNumberTV = findViewById(R.id.serialNumberTV)
        placeTV = findViewById(R.id.placeTV)

        barcodeTV = findViewById(R.id.barcodeTV)
        nameTV = findViewById(R.id.nameTV)
        btnNewScan =findViewById(R.id.btnNewScan)


    }


    override fun onStart() {
        super.onStart()
        val intent = intent

        val barCode:String?= intent.getStringExtra("BAR_CODE")
        /*var barCode:String ="B0302798"*/

        var baseUrl:String = """$BASE_URL$barCode/"""


        try {

            InventoryController().getInventorys(baseUrl)
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ InventoryData ->
                    Log.d("Adat érkezett", "méret ${InventoryData.id}")
                    barcodeTV?.text = "A leolvasott kód:" + InventoryData.vonalkod.toString()
                    deviceNameTV?.text = "Eszköz megnevezése:" + InventoryData.megnevezes.toString()
                    serialNumberTV?.text = "Sorozatszáma:" + InventoryData.gyariSzam.toString()
                    placeTV?.text =
                        "A felhasználó neve:" + InventoryData.leltarKorzetMegnevezes.toString()
                    nameTV?.text = "A leltarkörzet neve:" + InventoryData.leltSzemelyNeve.toString()


                }, {
                    Log.e("Adat hiba", it.message)
                    it.printStackTrace()
                })

        }
        catch (e: Exception){
            Toast.makeText(this,"Nincs kapcsolat a szerverrel!! $BASE_URL",Toast.LENGTH_LONG).show()
        }
        finally {
            btnNewScan?.setOnClickListener { finish() }
        }


    }


}
