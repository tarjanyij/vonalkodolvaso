package com.tarjanyi.vonalkodolvaso

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.Result
import com.tarjanyi.vonalkodolvaso.R.layout.activity_main
import me.dm7.barcodescanner.zxing.ZXingScannerView



class MainActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private val REQUEST_CAMERA = 1
    private var scannerView :ZXingScannerView?=null
    private var txtResult : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        scannerView= findViewById(R.id.scanner)


        txtResult = findViewById(R.id.textResult)
        if (!checkPermission())
            requestPermission()
    }

    private fun checkPermission() : Boolean{
        return ContextCompat.checkSelfPermission(  this@MainActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }
    private fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),REQUEST_CAMERA)
    }

    override fun onResume() {
        super.onResume()
        if (checkPermission()){
            if (scannerView == null){
               scannerView= findViewById(R.id.scanner)
               scannerView = ZXingScannerView(this)
                setContentView(scannerView)
            }
            scannerView?.setResultHandler(this)
            scannerView?.startCamera()
        }
    }

    override fun onDestroy() {
        scannerView?.stopCamera()
        super.onDestroy()

    }
    override fun handleResult(p0: Result?) {
        val result:String? = p0?.text
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
        txtResult?.text = result
        scannerView?.setResultHandler(this)

        val intent = Intent(this, DataPublisher::class.java)
        intent.putExtra("BAR_CODE",result)
        txtResult?.text = null
        startActivity(intent)

        /*scannerView?.startCamera()*/

    }
}