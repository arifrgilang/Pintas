package com.rz.pintas

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.Duration

class UnpadWifiReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("UnpadWifiReceiver","onReceive")
//        isConnectedToUnpadWifi(context)
    }

    private fun isConnectedToUnpadWifi(ctx: Context) {
        val wifiManager: WifiManager = ctx.applicationContext
            .getSystemService(Context.WIFI_SERVICE) as WifiManager
        if(wifiManager.isWifiEnabled){
            val info = wifiManager.connectionInfo
            Log.d("UnpadWifiReceiver", "Wifi is on")
//            if(info.ssid.equals("UnpadWifi")){
//                toast("SSID is UnpadWifi")
//                return true
//            } else {
//                toast("SSID is not UnpadWifi")
//            }
        }
//        return false
    }
}
