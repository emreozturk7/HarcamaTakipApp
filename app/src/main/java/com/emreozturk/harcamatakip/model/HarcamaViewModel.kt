package com.emreozturk.harcamatakip.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.emreozturk.harcamatakip.database.HarcamaRepository
import com.emreozturk.harcamatakip.database.HarcamaTablo
import com.emreozturk.harcamatakip.database.Veritabani
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HarcamaViewModel(application: Application):AndroidViewModel(application) {

    val tumVeriyiOku: LiveData<List<HarcamaTablo>>
    private val dao = Veritabani.ornegiGetir(application).erisimNesnesi()
    private var repository = HarcamaRepository(dao)

    init {
        val erisimNesnesi = Veritabani.ornegiGetir(application).erisimNesnesi()
        repository = HarcamaRepository(erisimNesnesi)
        tumVeriyiOku = repository.tumVeriyiOku
    }

    fun harcamaEkle(harcama: HarcamaTablo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.harcamaEkle(harcama)
        }
    }

    fun harcamaSil(harcama: HarcamaTablo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.harcamaSil(harcama)
        }
    }
}