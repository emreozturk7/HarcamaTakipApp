package com.emreozturk.harcamatakip.database

import androidx.lifecycle.LiveData

class HarcamaRepository (private val erisimNesnesi: VeritabaniErisimNesnesi){

    val tumVeriyiOku: LiveData<List<HarcamaTablo>> = erisimNesnesi.tumVerileriGetir()

    suspend fun harcamaEkle(harcamaTablo: HarcamaTablo){
        erisimNesnesi.ekle(harcamaTablo)
    }

    suspend fun harcamaSil(harcamaTablo: HarcamaTablo){
        erisimNesnesi.harcamaSil(harcamaTablo)
    }
}