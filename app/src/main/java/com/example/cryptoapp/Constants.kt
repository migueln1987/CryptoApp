package com.example.cryptoapp

object Constants {

    const val API_KEY = BuildConfig.ApiKey
    const val WS_COIN_URL = "wss://ws.coinapi.io/v1/"
    const val HELLO_MESSAGE = "{\n" +
            "  \"type\": \"hello\",\n" +
            "  \"apikey\": \"$API_KEY\",\n" +
            "  \"heartbeat\": false,\n" +
            "  \"subscribe_data_type\": [\"trade\"],\n" +
            "  \"subscribe_filter_symbol_id\": [\n" +
            "    \"BITSTAMP_SPOT_BTC_USD\",\n" +
            "    \"BITFINEX_SPOT_BTC_LTC\",\n" +
            "    \"COINBASE_\",\n" +
            "    \"ITBIT_\"\n" +
            "    ]\n" +
            "}"
}