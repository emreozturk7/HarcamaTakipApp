package com.emreozturk.harcamatakip.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VeritabaniErisimNesnesi {
    @Insert
    suspend fun ekle(harcamaTablo: HarcamaTablo)

    @Query("SELECT * FROM harcama_tablo ORDER BY id DESC")
    fun tumVerileriGetir(): LiveData<List<HarcamaTablo>>

    @Query("SELECT * FROM harcama_tablo WHERE id = :id")
    suspend fun getir(id: Int) : HarcamaTablo?

    @Query("SELECT * FROM harcama_tablo ORDER BY id DESC LIMIT 1")
    suspend fun sonHarcamaGetir(): HarcamaTablo?

    @Delete
    suspend fun harcamaSil(harcamaTablo: HarcamaTablo)
}