package com.emreozturk.harcamatakip.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.emreozturk.harcamatakip.R
import com.google.android.material.button.MaterialButton

class OnBoardingScreen : Fragment() {

    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_on_boarding_screen, container, false)

        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)

        return view
    }

    private fun setOnboardingItems(){
        onboardingItemsAdapter =OnboardingItemsAdapter(
            listOf(
                OnBoardingItem(
                    onboardingImage = R.drawable.task,
                    title = "deneme",
                    description = "deneme"
                ),
                OnBoardingItem(
                    onboardingImage = R.drawable.time,
                    title = "deneme",
                    description = "deneme"
                ),
                OnBoardingItem(
                    onboardingImage = R.drawable.reminder,
                    title = "deneme",
                    description = "deneme"
                )
            )
        )
        val onboardingViewPager = requireView().findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }

        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        requireView().findViewById<ImageView>(R.id.imageNext).setOnClickListener{
            if(onboardingViewPager.currentItem + 1 < onboardingItemsAdapter.itemCount){
                onboardingViewPager.currentItem += 1
            }else{
                navigateToMain()
            }
        }
        requireView().findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToMain()
        }
        requireView().findViewById<MaterialButton>(R.id.buttonGetStarted).setOnClickListener {
            navigateToMain()
        }
    }

    private fun navigateToMain(){
        findNavController().navigate(R.id.action_onBoardingScreen_to_main)
    }

    private fun setupIndicators(){
        indicatorsContainer = requireView().findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(requireContext())
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }
    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if(i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_active_background
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}