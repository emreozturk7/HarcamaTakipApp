package com.emreozturk.harcamatakip.api

import com.google.gson.annotations.SerializedName

data class Doviz(
    @SerializedName("EUR_TRY")
    val eURTRY: Double,
    @SerializedName("GBP_TRY")
    val gBPTRY: Double,
    @SerializedName("USD_TRY")
    val uSDTRY: Double
)