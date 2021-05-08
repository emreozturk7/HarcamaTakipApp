package com.emreozturk.harcamatakip.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.emreozturk.harcamatakip.R
import kotlinx.android.synthetic.main.fragment_isim_degis.*
import kotlinx.android.synthetic.main.fragment_isim_degis.view.*

class IsimDegis : Fragment() {

    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = this.requireActivity().getSharedPreferences("package com.emreozturk.harcamatakip.fragments",
            Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_isim_degis, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isimDegisButton.setOnClickListener {
            save(view)
            findNavController().navigate(R.id.action_isimDegis_to_main)
        }
    }

    fun save(view: View){

        val isim= view.isimGirinizTextView.text.toString()
        var cinsiyet : String? = null

        if(erkekRadioButton.isChecked){
            cinsiyet = "Bey"
        }
        else if(kadinRadioButton.isChecked){
            cinsiyet = "Hanım"
        }
        else if(belirtisizRadioButton.isChecked){
            cinsiyet = ""
        }

        sharedPreferences.edit().putString("isim",isim).apply()

        sharedPreferences.edit().putString("cinsiyet",cinsiyet).apply()
    }
}