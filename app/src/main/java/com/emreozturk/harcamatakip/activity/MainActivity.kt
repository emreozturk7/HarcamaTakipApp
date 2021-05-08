package com.emreozturk.harcamatakip.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.emreozturk.harcamatakip.R
import kotlinx.android.synthetic.main.fragment_isim_degis.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
    }
}