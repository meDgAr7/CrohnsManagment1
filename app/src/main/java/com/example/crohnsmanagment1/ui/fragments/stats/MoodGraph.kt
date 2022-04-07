package com.example.crohnsmanagment1.ui.fragments.stats

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.ui.viewmodels.MoodViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.fragment_mood_graph.*


class MoodGraph : Fragment(R.layout.fragment_mood_graph) {

    private lateinit var moodViewModel: MoodViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moodViewModel = ViewModelProvider(this).get(MoodViewModel::class.java)

        setUpBarChart()

        populateBarChart()


    }

    private fun setUpBarChart(){
        barChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawLabels(false)
            axisLineColor = Color.BLACK
            textColor = Color.BLACK
            setDrawGridLines(false)
        }
        barChart.axisLeft.apply {
            axisLineColor = Color.BLACK
            textColor = Color.BLACK
            setDrawGridLines(false)

        }.isInverted
        barChart.axisRight.apply {
            axisLineColor = Color.BLACK
            textColor = Color.BLACK
            setDrawGridLines(false)
        }.isInverted

        barChart.apply {
            description.text = "Mood Over Time"
            legend.isEnabled = false
        }
    }

    private fun populateBarChart(){

        moodViewModel.getAllMood.observe(viewLifecycleOwner, Observer {
            it?.let{
                val allMood = it.indices.map { i -> BarEntry(i.toFloat(), it[i].mood_rating.toFloat()) }
                val allStress = it.indices.map { i -> BarEntry(i.toFloat(), it[i].mood_stress.toFloat()) }

                val bardataSet1 = BarDataSet(allMood, "Mood Over Time").apply {
                    valueTextColor = Color.BLACK
                    color = ContextCompat.getColor(requireContext(), R.color.purple_700)
                }
                val bardataSet2 = BarDataSet(allStress, "Stress Levels over time").apply {
                    valueTextColor = Color.BLACK
                    color = ContextCompat.getColor(requireContext(), R.color.teal_200)
                }

                //set bar chart
                barChart.data = BarData(bardataSet1, bardataSet2)
            }
        })
    }

}