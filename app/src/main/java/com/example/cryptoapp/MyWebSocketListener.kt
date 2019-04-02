package com.example.cryptoapp

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class MyWebSocketListener: WebSocketListener() {

    lateinit var currentObject : WSResponse
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
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        Log.d(TAG, "onClosed: $code $reason")

    }
}