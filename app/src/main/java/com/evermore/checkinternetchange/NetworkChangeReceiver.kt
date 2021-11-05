package com.evermore.checkinternetchange

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.util.Log
import com.evermore.checkinternetchange.NetworkUtil.getConnectivityStatusString

class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val connectivityManager by lazy {context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

        Log.e("bibby1104", "network reciever")
        Log.e("bibby1104", intent.action.toString())
        Log.e("bibby1104", intent.data.toString())
        intent.dataString?.let { Log.e("bibby1104", it) }


        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            var wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0)
            Log.e("bibby1104", "${wifiState}")

            // WifiManager.WIFI_STATE_DISABLED 1
            // WifiManager.WIFI_STATE_DISABLING 0
            //WifiManager.WIFI_STATE_ENABLED 3
            //WifiManager.WIFI_STATE_ENABLING 2
            //WifiManager.WIFI_STATE_UNKNOWN 4

            //int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
//            (wifiState) {
//                WifiManager.WIFI_STATE_DISABLED:
//                Log.d("Tag", "--- WIFI狀態：已關閉WIFI功能 ---");
//                break;
//                case WifiManager.WIFI_STATE_DISABLING:
//                Log.d("Tag", "--- WIFI狀態：正在關閉WIFI功能 ---");
//                break;
//            }

        }


//        connectivityManager.activeNetwork?.let { activeNetwork ->
//            connectivityManager.getNetworkCapabilities(activeNetwork)?.let { capabilities ->
//                Log.d("bibby", "capabilities")
//                Log.d("bibby", "${capabilities.linkDownstreamBandwidthKbps}")
//                Log.d("bibby", "${capabilities.linkUpstreamBandwidthKbps}")
//                Log.d("bibby", "${capabilities.toString()}")
//                when {
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                        //TODO("WIFI?")
//                        Log.d("bibby", "WIFI?")
//
//                        //有連WIFI, 但刻意把AP的網路線拔掉
//                        //2021-06-23 14:46:29.455 8918-8918/com.evermore.checkinternetchange D/bibby: capabilities
//                        //2021-06-23 14:46:29.455 8918-8918/com.evermore.checkinternetchange D/bibby: 1048576
//                        //2021-06-23 14:46:29.455 8918-8918/com.evermore.checkinternetchange D/bibby: 1048576
//                        //2021-06-23 14:46:29.455 8918-8918/com.evermore.checkinternetchange D/bibby: [ Transports: WIFI Capabilities: NOT_METERED&INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&NOT_ROAMING&FOREGROUND&NOT_CONGESTED&NOT_SUSPENDED LinkUpBandwidth>=1048576Kbps LinkDnBandwidth>=1048576Kbps SignalStrength: -47]
//
//                        //有連WIFI, AP也有對外網路
//                        //多一個已驗證VALIDATED
//                        //2021-06-23 14:55:05.764 8918-8918/com.evermore.checkinternetchange D/bibby: capabilities
//                        //2021-06-23 14:55:05.764 8918-8918/com.evermore.checkinternetchange D/bibby: 1048576
//                        //2021-06-23 14:55:05.764 8918-8918/com.evermore.checkinternetchange D/bibby: 1048576
//                        //2021-06-23 14:55:05.764 8918-8918/com.evermore.checkinternetchange D/bibby: [ Transports: WIFI Capabilities: NOT_METERED&INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&VALIDATED&NOT_ROAMING&FOREGROUND&NOT_CONGESTED&NOT_SUSPENDED LinkUpBandwidth>=1048576Kbps LinkDnBandwidth>=1048576Kbps SignalStrength: -47]
//
//                    }
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                        //TODO("4G?")
//                        Log.d("bibby", "4G?")
//
//                        //2021-06-23 14:43:38.412 8918-8918/com.evermore.checkinternetchange D/bibby: capabilities
//                        //2021-06-23 14:43:38.412 8918-8918/com.evermore.checkinternetchange D/bibby: 30000
//                        //2021-06-23 14:43:38.412 8918-8918/com.evermore.checkinternetchange D/bibby: 15000
//                        //2021-06-23 14:43:38.412 8918-8918/com.evermore.checkinternetchange D/bibby: [ Transports: CELLULAR Capabilities: MMS&SUPL&INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&VALIDATED&NOT_ROAMING&FOREGROUND&NOT_CONGESTED&NOT_SUSPENDED LinkUpBandwidth>=15000Kbps LinkDnBandwidth>=30000Kbps Specifier: <2>]
//                        //2021-06-23 14:43:38.412 8918-8918/com.evermore.checkinternetchange D/bibby: 4G?
//
//                    }
//                    else -> {
//                        //TODO("OTHER?")
//                        Log.d("bibby", "OTHER?")
//
//
//
//                    }
//                }
//            } ?: let {
//                //TODO("無法取得目前網路資訊?")
//                Log.d("bibby", "無法取得目前網路資訊?")
//
//
//
//            }
//        } ?: let {
//            //TODO("沒有對外網路?")
//            Log.d("bibby", "沒有對外網路?")
//
//            //WIFI關掉 跟 4G關掉
//
//        }
//
//
//
//        val status = getConnectivityStatusString(context)
//
//        Log.e("bibby", status.toString())
//        if ("android.net.conn.CONNECTIVITY_CHANGE" == intent.action) {
//            if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
//                //ForceExitPause(context).execute()
//                Log.e("bibby", "NETWORK_STATUS_NOT_CONNECTED")
//            } else if(status == NetworkUtil.NETWORK_STATUS_MOBILE) {
//                Log.e("bibby", "NETWORK_STATUS_MOBILE")
//            } else if(status == NetworkUtil.NETWORK_STATUS_WIFI) {
//                Log.e("bibby", "NETWORK_STATUS_WIFI")
//            } else {
//                //ResumeForceExitPause(context).execute()
//                Log.e("bibby", "status > ${status}")
//            }
//        }
    }
}