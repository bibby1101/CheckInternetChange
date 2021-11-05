package com.evermore.checkinternetchange

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val connectivityManager by lazy {getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val intentFilter = IntentFilter()
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
//        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED")
//        networkChangeReceiver = NetworkChangeReceiver()
//        registerReceiver(networkChangeReceiver, intentFilter)

        connectivityManager.activeNetwork?.let { activeNetwork ->
            connectivityManager.getNetworkCapabilities(activeNetwork)?.let { capabilities ->
                    Log.d("bibby", "capabilities")
                    Log.d("bibby", "${capabilities.linkDownstreamBandwidthKbps}")
                    Log.d("bibby", "${capabilities.linkUpstreamBandwidthKbps}")
                    Log.d("bibby", "${capabilities.toString()}")
                    Log.d("bibby", "${capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)}")
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            //TODO("WIFI?")
                            Log.d("bibby", "WIFI?")
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            //TODO("4G?")
                            Log.d("bibby", "4G?")
                        }
                        else -> {
                            //TODO("OTHER?")
                            Log.d("bibby", "OTHER?")
                        }
                    }
            } ?: let {
                //TODO("無法取得目前網路資訊?")
                Log.d("bibby", "無法取得目前網路資訊?")
            }
        } ?: let {
            //TODO("沒有對外網路?")
            Log.d("bibby", "沒有對外網路?")
            // WIFI關掉 跟 4G沒網路
            // WIFI關掉 跟 4G關掉
            // WIFI沒網路 跟 4G關掉
            // WIFI沒網路 跟 4G沒網路
        }

        connectivityManager.registerDefaultNetworkCallback(networkListener)
        // 一註冊完CallBack不會收到事件
        // 狀況1. WIFI關掉 & 關閉行動數據
        // 狀況2. 飛航模式
        //
        //

        // 一註冊完CallBack會馬上收到事件
        // 狀況1. WIFI沒網路 & 關閉行動數據
        // 狀況2. WIFI關掉 & 開啟4G(不管到底有沒有配網路)
        //
        //
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            connectivityManager.unregisterNetworkCallback(networkListener)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if(::networkChangeReceiver.isInitialized)
            unregisterReceiver(networkChangeReceiver)
    }

    private val networkListener = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d("bibby1104", "onAvailable > ${network.toString()}")

//            connectivityManager.activeNetwork?.let { activeNetwork ->
//                connectivityManager.getNetworkCapabilities(activeNetwork)?.let { capabilities ->
//                    Log.d("bibby", "capabilities")
//                    Log.d("bibby", "${capabilities.linkDownstreamBandwidthKbps}")
//                    Log.d("bibby", "${capabilities.linkUpstreamBandwidthKbps}")
//                    Log.d("bibby", "${capabilities.toString()}")
//                    Log.d("bibby", "${capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)}")
//                    when {
//                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                            //TODO("WIFI?")
//                            Log.d("bibby", "WIFI?")
//                        }
//                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                            //TODO("4G?")
//                            Log.d("bibby", "4G?")
//                        }
//                        else -> {
//                            //TODO("OTHER?")
//                            Log.d("bibby", "OTHER?")
//                        }
//                    }
//                } ?: let {
//                    //TODO("無法取得目前網路資訊?")
//                    Log.d("bibby", "無法取得目前網路資訊?")
//                }
//            } ?: let {
//                //TODO("沒有對外網路?")
//                Log.d("bibby", "沒有對外網路?")
//                //WIFI關掉 跟 4G關掉
//            }

        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d("bibby1104", "onLost > ${network.toString()}")
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            Log.d("bibby1104", "onLosing > ${network.toString()}")
            Log.d("bibby1104", "onLosing > ${maxMsToLive.toString()}")
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            Log.d("bibby1104", "onCapabilitiesChanged > ${network.toString()}")
            Log.d("bibby1104", "onCapabilitiesChanged > ${networkCapabilities.toString()}")

            when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    //TODO("WIFI?")
                    Log.d("bibby1104", "WIFI?")
                }
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    //TODO("4G?")
                    Log.d("bibby1104", "4G?")
                }
                else -> {
                    //TODO("OTHER?")
                    Log.d("bibby1104", "OTHER?")
                }
            }

            val online = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            Log.d("bibby1104", "onCapabilitiesChanged > ${online}")

        }

        override fun onUnavailable() {
            super.onUnavailable()
            Log.d("bibby1104", "onUnavailable")
        }

        override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
            super.onBlockedStatusChanged(network, blocked)
            Log.d("bibby1104", "onBlockedStatusChanged > ${network.toString()}")
            Log.d("bibby1104", "onBlockedStatusChanged > ${blocked}")

//            connectivityManager.activeNetwork?.let { activeNetwork ->
//                connectivityManager.getNetworkCapabilities(activeNetwork)?.let { capabilities ->
//                    Log.d("bibby", "capabilities")
//                    Log.d("bibby", "${capabilities.linkDownstreamBandwidthKbps}")
//                    Log.d("bibby", "${capabilities.linkUpstreamBandwidthKbps}")
//                    Log.d("bibby", "${capabilities.toString()}")
//                    Log.d("bibby", "${capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)}")
//                    when {
//                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                            //TODO("WIFI?")
//                            Log.d("bibby", "WIFI?")
//                        }
//                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                            //TODO("4G?")
//                            Log.d("bibby", "4G?")
//                        }
//                        else -> {
//                            //TODO("OTHER?")
//                            Log.d("bibby", "OTHER?")
//                        }
//                    }
//                } ?: let {
//                    //TODO("無法取得目前網路資訊?")
//                    Log.d("bibby", "無法取得目前網路資訊?")
//                }
//            } ?: let {
//                //TODO("沒有對外網路?")
//                Log.d("bibby", "沒有對外網路?")
//                //WIFI關掉 跟 4G關掉
//            }

        }

        override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
            super.onLinkPropertiesChanged(network, linkProperties)
            Log.d("bibby1104", "onLinkPropertiesChanged > ${network.toString()}")
            Log.d("bibby1104", "onLinkPropertiesChanged > ${linkProperties.toString()}")
        }
    }
}