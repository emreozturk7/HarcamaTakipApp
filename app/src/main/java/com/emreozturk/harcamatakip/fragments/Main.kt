package com.emreozturk.harcamatakip.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColor
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.emreozturk.harcamatakip.R
import com.emreozturk.harcamatakip.adapter.ListAdapter
import com.emreozturk.harcamatakip.api.Doviz
import com.emreozturk.harcamatakip.api.CurrencyAPI
import com.emreozturk.harcamatakip.model.HarcamaViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var dolar = 0.0
var euro = 0.0
var sterlin = 0.0

class Main : Fragment() {

    private lateinit var mHarcamaViewModel: HarcamaViewModel

    lateinit var sharedPreferences : SharedPreferences

    var usdTry = 8.0
    var euroTry = 9.0
    var gbpTry = 10.0

    val BASE_URL = "https://free.currconv.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = this.requireActivity().getSharedPreferences("package com.emreozturk.harcamatakip.fragments",
            Context.MODE_PRIVATE)

        getCurrencyRates()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.currencyList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)
        mHarcamaViewModel.tumVeriyiOku.observe(viewLifecycleOwner, Observer {
            adapter.veriAyarla(it)
        })

        val isimPreferences = sharedPreferences.getString("isim","")
        val cinsiyetPreferences = sharedPreferences.getString("cinsiyet","")

        view.isimTextView.text = isimPreferences + " " +cinsiyetPreferences

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isimTextView.setOnClickListener {
            findNavController().navigate(R.id.action_main_to_isimDegis)
        }

        harcamaEkle.setOnClickListener {
            findNavController().navigate(R.id.action_main_to_harcamaEkle)
        }
    }

    fun getCurrencyRates() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CurrencyAPI::class.java)

        val call = service.getCurrencyDolar()
        val call2 = service.getCurrencyEuro()
        val call3 = service.getCurrencySterlin()

        call.enqueue(object : Callback<Doviz> {
            override fun onFailure(call: Call<Doviz>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Doviz>, response: Response<Doviz>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        usdTry= it.uSDTRY
                        //println(usdTry)
                        dolar = usdTry
                    }
                }
            }
        })

        call2.enqueue(object : Callback<Doviz> {
            override fun onFailure(call2: Call<Doviz>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call2: Call<Doviz>, response: Response<Doviz>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        euroTry= it.eURTRY
                        //println(euroTry)
                        euro = euroTry
                    }
                }
            }
        })
        call3.enqueue(object : Callback<Doviz> {
            override fun onFailure(call3: Call<Doviz>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call3: Call<Doviz>, response: Response<Doviz>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        gbpTry= it.gBPTRY
                        //println(gbpTry)
                        sterlin = gbpTry
                    }
                }
            }
        })
    }
}