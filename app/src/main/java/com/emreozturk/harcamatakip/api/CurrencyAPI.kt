package com.emreozturk.harcamatakip.api

import retrofit2.Call
import retrofit2.http.GET

interface CurrencyAPI {
    @GET("/api/v7/convert?q=USD_TRY&compact=ultra&apiKey=5197fd52a6ab55f0072b")
    fun getCurrencyDolar() : Call<Doviz>
    @GET("/api/v7/convert?q=EUR_TRY&compact=ultra&apiKey=5197fd52a6ab55f0072b")
    fun getCurrencyEuro() : Call<Doviz>
    @GET("/api/v7/convert?q=GBP_TRY&compact=ultra&apiKey=5197fd52a6ab55f0072b")
    fun getCurrencySterlin() : Call<Doviz>
}