package com.tarjanyi.vonalkodolvaso

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DataPublisher : AppCompatActivity() {
    private var barcode : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_publisher)

        barcode = findViewById(R.id.barcode)

    }

    override fun onStart() {
        super.onStart()
        val intent = getIntent()

        val codeString:String?= intent.getStringExtra("BAR_CODE")
        barcode?.text = "A leolvasott kód:"+codeString

    }

    fun newScan(view: View) {
        finish()
    }
}
