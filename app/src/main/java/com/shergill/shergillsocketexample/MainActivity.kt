package com.shergill.shergillsocketexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.piesocket.channels.Channel
import com.piesocket.channels.PieSocket
import com.piesocket.channels.misc.PieSocketEvent
import com.piesocket.channels.misc.PieSocketEventListener
import com.piesocket.channels.misc.PieSocketOptions
import com.shergill.shergillsocketexample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private var piesocket: PieSocket? = null
    private var channel: Channel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
        val pieSocketOptions = PieSocketOptions()
        pieSocketOptions.clusterId = "free.blr2"
        pieSocketOptions.apiKey = "p3X3pnu2uqlblhxiFhJMwK80ONeRmJJZe4vppwbQ"
        piesocket = PieSocket(pieSocketOptions)
        channel = piesocket!!.join("1")
        channel?.listen("system:connected", object : PieSocketEventListener() {
            override fun handleEvent(event: PieSocketEvent) {
                Log.d("PIESOCKET-SDK", "Channel connected")
                val clientEvent = PieSocketEvent("testevent")
                clientEvent.data = "Hello!"
                channel?.publish(clientEvent)
            }
        })
        channel?.listen("testevent", object : PieSocketEventListener() {
            override fun handleEvent(event: PieSocketEvent) {
                Log.d("PIESOCKET-SDK", "testevent was fired")
            }
        })
        ///satta sher gill
    }
}