package com.example.crohnsmanagment1.ui.fragments.stats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.crohnsmanagment1.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.fragment_stats.*

class Stats : Fragment(R.layout.fragment_stats) {

    private lateinit var viewPager: ViewPager2
    var tabTitle = arrayOf("Exercise","Mood","BM", "Weight")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.pager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        pager.adapter = StatsAdapter(childFragmentManager, lifecycle)

         TabLayoutMediator(tabLayout,viewPager){

             tab, position ->
             tab.text = tabTitle[position]
         }.attach()

    }
}