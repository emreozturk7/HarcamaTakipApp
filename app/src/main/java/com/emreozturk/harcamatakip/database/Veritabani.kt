package com.emreozturk.harcamatakip.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [HarcamaTablo::class], version = 1,exportSchema = false)
abstract class Veritabani : RoomDatabase(){

    abstract fun erisimNesnesi(): VeritabaniErisimNesnesi

    companion object{
        @Volatile
        private var ORNEK_NESNE: Veritabani? = null

        fun ornegiGetir(baglam: Context):Veritabani{
            val gecici = ORNEK_NESNE
            if(gecici != null){
                return gecici
            }
            synchronized(this){
                val ornek = Room.databaseBuilder(
                    baglam.applicationContext,
                    Veritabani::class.java,
                    "harcama_veritabani"
                ).build()
                ORNEK_NESNE = ornek
                return ornek
            }
        }
    }
}