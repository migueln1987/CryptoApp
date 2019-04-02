package com.example.cryptoapp.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cryptoapp.Constants
import com.example.cryptoapp.R
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_main2.*
import okhttp3.*


class Main2Activity : AppCompatActivity(), ISocketData {
    companion object {
        const val TAG = "Main2Activity"
    }
    override fun newData(response: WSResponse) {
        Log.d(TAG, response.toString())
        runOnUiThread {
            symbol_id.text = response.symbol_id
            tv_display.text = response.price.toString()
        }
    }

    val client = OkHttpClient()
    val appRequest = Request.Builder().url(Constants.WS_COIN_URL).build()
    lateinit var listener: WebSocketListener
    lateinit var socketData: ISocketData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        socketData = this
        toggle_button.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> start()
                false -> stop()
            }
        }
    }

    private fun start() {
        getPrice()

    }

    private fun stop() {
    }

    fun getPrice() {

        lateinit var currentObject: WSResponse

        listener = object : WebSocketListener() {

            val TAG = "WebSocketListener"

            override fun onOpen(webSocket: WebSocket, response: Response) {
                webSocket.send(Constants.HELLO_MESSAGE)
                Log.d(TAG, "onOpen()")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.d(TAG, "Throwable: ${t.message.toString()} Respose: $response")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.close(1000, "Done")
                webSocket.cancel()
                Log.d(TAG, "onClosing: $code $reason")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d(TAG, "onMessage: $text")
                val parser = JsonParser()
                val mJson = parser.parse(text)
                val gson = Gson()
                currentObject = gson.fromJson(mJson, WSResponse::class.java)
                socketData.newData(currentObject)

            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.d(TAG, "onClosed: $code $reason")

            }
        }
        client.newWebSocket(appRequest, listener)
        client.dispatcher().executorService().shutdown()
    }

}

