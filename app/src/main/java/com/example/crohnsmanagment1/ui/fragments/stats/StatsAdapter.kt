package com.example.crohnsmanagment1.ui.fragments.stats

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.crohnsmanagment1.ui.fragments.stats.ExerciseGraph
import com.example.crohnsmanagment1.ui.fragments.stats.MoodGraph
import com.example.crohnsmanagment1.ui.fragments.stats.StoolGraph
import com.example.crohnsmanagment1.ui.fragments.stats.WeightGraph

class StatsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ExerciseGraph()
            1 -> return MoodGraph()
            2 -> return StoolGraph()
            3 -> return WeightGraph()
            else -> return ExerciseGraph()
        }
    }
}