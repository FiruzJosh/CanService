package ru.kamaz.canservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import bw.car.hardware.vehicle.CarVehiclePropertyIds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    lateinit var can : ru.kamaz.canservice.aidl.AidleForCan

    var sercon = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            Log.i("qwer", "start")
            can = ru.kamaz.canservice.aidl.AidleForCan.Stub.asInterface(p1)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.i("qwer", "end")
        }

    }

    override fun onStart() {
        super.onStart()
        bindService(createExplicitIntent(),sercon, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        //unbindService(sercon)
    }

    private fun createExplicitIntent(): Intent {
        val intent = Intent("ru.kamaz.canservice.aidl.REMOTE_CONNECTION")
        val services = packageManager.queryIntentServices(intent, 0)
        if (services.isEmpty()) {
            throw IllegalStateException("Приложение-сервер не установлено")
        }
        return Intent(intent).apply {
            val resolveInfo = services[0]
            val packageName = resolveInfo.serviceInfo.packageName
            val className = resolveInfo.serviceInfo.name
            component = ComponentName(packageName, className)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ImageView>(R.id.imageView).setOnClickListener(View.OnClickListener {
            can.sendCan(arrayOf(CanDataInfo(0x18FEEF00,1,3,16,1,0,5000f),CanDataInfo(0x18FEEF00,3,4,8,1,0,200f)))
            //can.sendAllCan(0x18FEEF00, byteArrayOf(255.toByte(),0,0,0,0,0,0,100))
            //Log.i("cantq", java.lang.Long.decode("0x7B000000000000AA").toString())
        })
        findViewById<ImageView>(R.id.getq).setOnClickListener(View.OnClickListener {
            Log.i("cantq", can.getCan(0x18FF211E,3,0,8,1,0).toString())
            var cal = Calendar.getInstance()
            Log.i("cantq",cal.toString())
            cal.timeInMillis = (can.getCan(0x18FF211E,3,0,8,1,0).time).toLong()*1000
            Log.i("cantq",cal.toString())
        })
        findViewById<ImageView>(R.id.vinb).setOnClickListener(View.OnClickListener {can.setVin()})
        findViewById<ImageView>(R.id.vinbget).setOnClickListener(View.OnClickListener {vin(can.vin)})
    }

    fun vin(vin:String){
        findViewById<TextView>(R.id.vin).text = vin
    }
}