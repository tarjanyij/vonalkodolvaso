package com.tarjanyi.vonalkodolvaso

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DataPublisher : AppCompatActivity() {
    private var barcode : TextView? = null
    private var btnNewScan : Button? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_publisher)

        barcode = findViewById(R.id.barcode)
        btnNewScan =findViewById(R.id.btnNewScan)


    }

    override fun onStart() {
        super.onStart()
        val intent = getIntent()

        val codeString:String?= intent.getStringExtra("BAR_CODE")
        barcode?.text = "A leolvasott kód:"+codeString

        btnNewScan?.setOnClickListener { finish() }

    }


}
