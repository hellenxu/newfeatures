package six.ca.newfeatures.androidp

import android.app.Activity
import android.content.Context
import android.net.wifi.WifiManager
import android.net.wifi.rtt.RangingRequest
import android.net.wifi.rtt.RangingResult
import android.net.wifi.rtt.RangingResultCallback
import android.net.wifi.rtt.WifiRttManager
import android.os.Bundle
import java.util.concurrent.Executors

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-09-18.
 */
class WifiRttExample: Activity() {
    private lateinit var wifiRttManager: WifiRttManager
    private lateinit var wifiManager: WifiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        wifiRttManager = getSystemService(Context.WIFI_RTT_RANGING_SERVICE) as WifiRttManager
        wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager

        if(wifiRttManager.isAvailable) {
            val request: RangingRequest = RangingRequest.Builder()
                    .addAccessPoints(wifiManager.scanResults)
                    .build()

            val callback = object : RangingResultCallback() {
                override fun onRangingFailure(code: Int) {
                    println("xxl-failure-00: $code")
                }

                override fun onRangingResults(list: List<RangingResult>) {
                    list.forEach {
                        println("xxl-result-000: $it")
                    }                }
            }

            wifiRttManager.startRanging(request, executor = Executors.newSingleThreadExecutor(), callback)
        }

    }
}