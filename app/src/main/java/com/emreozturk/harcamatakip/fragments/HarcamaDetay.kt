package com.emreozturk.harcamatakip.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.emreozturk.harcamatakip.R
import com.emreozturk.harcamatakip.model.HarcamaViewModel
import kotlinx.android.synthetic.main.fragment_harcama_detay.view.*

class HarcamaDetay : Fragment() {

    private val args by navArgs <HarcamaDetayArgs>()
    private lateinit var mHarcamaViewModel: HarcamaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_harcama_detay, container, false)

        val context: Context = this.requireContext()

        if (args.seciliHarcama.icon.equals("Kira")) {
            view.silDetayIconImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ev
                )
            )
        }

        else if (args.seciliHarcama.icon.equals("Fatura")) {
            view.silDetayIconImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.fatura
                )
            )
        }

        else if (args.seciliHarcama.icon.equals("Diğer")) {
            view.silDetayIconImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.sepet
                )
            )
        }

        view.silAciklamaTextView.setText(args.seciliHarcama.tur)
        view.silHarcamaTextView.setText(args.seciliHarcama.fiyat.toString())

        view.silButon.setOnClickListener {
            harcamaSil()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun harcamaSil(){
        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Evet") { _ , _   ->
            mHarcamaViewModel.harcamaSil(args.seciliHarcama)
            Toast.makeText(
                requireContext(), "Başarıyla silindi: ${args.seciliHarcama.tur}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_harcamaDetay_to_main)
        }
        builder.setNegativeButton("Hayır") { _ , _ ->}
        builder.setTitle("${args.seciliHarcama.tur} silinsin mi?")
        builder.setMessage("${args.seciliHarcama.tur} silmek istediğinize emin misiniz?")
        builder.create().show()
    }
}