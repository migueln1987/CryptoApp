package com.example.cryptoapp.livedata

interface ISocketData {
    fun newData(response: WSResponse)
}
