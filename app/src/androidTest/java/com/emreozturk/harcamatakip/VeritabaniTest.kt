package com.emreozturk.harcamatakip

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.emreozturk.harcamatakip.database.HarcamaTablo
import com.emreozturk.harcamatakip.database.Veritabani
import com.emreozturk.harcamatakip.database.VeritabaniErisimNesnesi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class VeritabaniTest {
    private lateinit var veritabaniErisimNesnesi: VeritabaniErisimNesnesi
    private lateinit var veritabani: Veritabani

    @Before
    fun veritabaniOlustur() {
        val baglam = InstrumentationRegistry.getInstrumentation().targetContext

        veritabani = Room.inMemoryDatabaseBuilder(baglam, Veritabani::class.java)
            .allowMainThreadQueries()
            .build()
        veritabaniErisimNesnesi = veritabani.erisimNesnesi()
    }

    @After
    @Throws(IOException::class)
    fun veritabaniKapat() {
        veritabani.close()
    }

    @Test
    @Throws(Exception::class)
    fun birHarcamaEkleveOku() {
        runBlocking {
            val harcama = HarcamaTablo(0,"","",1)
            veritabaniErisimNesnesi.ekle(harcama)
            val sonEklenenHarcama = veritabaniErisimNesnesi.sonHarcamaGetir()
            assertEquals(sonEklenenHarcama?.durum, -1);
        }
    }
}