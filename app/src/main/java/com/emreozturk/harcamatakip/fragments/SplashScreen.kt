package com.emreozturk.harcamatakip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.emreozturk.harcamatakip.R
import java.lang.Exception

class SplashScreen : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val background = object : Thread() {
            override fun run(){
                try {
                    Thread.sleep(5000)
                    findNavController().navigate(R.id.action_splashScreen_to_main)
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
}