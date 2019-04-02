package com.example.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*

class MainActivity : AppCompatActivity() {
    val client = OkHttpClient()
    val appRequest = Request.Builder().url(Constants.WS_COIN_URL).build()
    lateinit var webSocket : WebSocket
    lateinit var listener : WebSocketListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toggle_button.setOnCheckedChangeListener{ _, isChecked ->
            when (isChecked) {
                true -> start()
                false -> stop()
            }
        }
    }

    private fun start() {
        listener = MyWebSocketListener()
        webSocket = client.newWebSocket(appRequest, listener)
        client.dispatcher().executorService().shutdown()
    }

    private fun stop() {
        listener.onClosing(webSocket, 1000, "DONE")
    }
}
