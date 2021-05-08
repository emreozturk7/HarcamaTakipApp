package com.emreozturk.harcamatakip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.emreozturk.harcamatakip.R
import com.emreozturk.harcamatakip.database.HarcamaTablo
import com.emreozturk.harcamatakip.fragments.*
import kotlinx.android.synthetic.main.fragment_harcama_detay.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.row_layout.view.*
import kotlinx.coroutines.selects.select

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var harcamaList = emptyList<HarcamaTablo>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return harcamaList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var seciliEleman = harcamaList[position]

        holder.itemView.turTextView.text = seciliEleman.tur
        holder.itemView.fiyatTextView.text = seciliEleman.fiyat.toString()

        val context: Context = holder.itemView.getContext()

        if (seciliEleman.icon.equals("Kira")) {
            holder.itemView.iconImage.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ev
                )
            )
        }

        else if (seciliEleman.icon.equals("Fatura")) {
             holder.itemView.iconImage.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.fatura
                )
            )
        }

        else if (seciliEleman.icon.equals("Diğer")) {
            holder.itemView.iconImage.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.sepet
                )
            )
        }

        holder.itemView.rowLayout.setOnClickListener {
            val action = MainDirections.actionMainToHarcamaDetay(seciliEleman)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun veriAyarla(harcamaTablo: List<HarcamaTablo>){
        this.harcamaList = harcamaTablo
        notifyDataSetChanged()
    }
}