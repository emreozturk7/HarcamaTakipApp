package com.emreozturk.harcamatakip.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.emreozturk.harcamatakip.R
import com.emreozturk.harcamatakip.database.HarcamaTablo
import com.emreozturk.harcamatakip.model.HarcamaViewModel
import kotlinx.android.synthetic.main.fragment_harcama_ekle.*
import kotlinx.android.synthetic.main.fragment_harcama_ekle.view.*


class HarcamaEkle : Fragment() {

    private lateinit var mHarcamaViewModel: HarcamaViewModel

    var dolarMiktar = dolar
    var euroMiktar = euro
    var sterlinMiktar = sterlin

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_harcama_ekle, container, false)

        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)

        view.ekleButton.setOnClickListener {
            VeriTabaninaVeriEkle()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun VeriTabaninaVeriEkle(){
        var icon = ""
        var aciklama= aciklamaEkleEditText.text.toString()
        var harcama : Int = Integer.parseInt(harcamaEditText.text.toString())

        when {
            kiraRadioButton.isChecked -> {
                icon = "Kira"
            }
            faturaRadioButton.isChecked -> {
                icon = "Fatura"
            }
            digerRadioButton.isChecked -> {
                icon = "Diğer"
            }
        }
        when {
            turkLiraRadioButton.isChecked -> {
                harcama = harcama * 1
            }
            dolarRadioButton.isChecked -> {
                harcama = harcama * dolarMiktar.toInt()
            }
            euroRadioButton.isChecked -> {
                harcama = harcama * euroMiktar.toInt()
            }
            sterlinRadioButton.isChecked -> {
                harcama = harcama * sterlinMiktar.toInt()
            }
        }

            val harcamaTablo = HarcamaTablo(0,icon,aciklama,Integer.parseInt(harcama.toString()))

            mHarcamaViewModel.harcamaEkle(harcamaTablo)

            Toast.makeText(requireContext(),"Ekleme Basarili.",Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_harcamaEkle_to_main)
    }
}
