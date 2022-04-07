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
import com.example.crohnsmanagment1.ui.viewmodels.ExerciseViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.fragment_mood_graph.*


class ExerciseGraph : Fragment(R.layout.fragment_exercise_graph) {

    private lateinit var exerciseViewModel: ExerciseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)

        setUpBarChart()

       // populateBarChart()


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
            description.text = "Exercise Over Time"
            legend.isEnabled = false
        }
    }

   private fun populateBarChart(){

        exerciseViewModel.getAllExercise.observe(viewLifecycleOwner, Observer {
            it?.let{
                val allExercise = it.indices.map { i -> BarEntry(i.toFloat(), it[i].exercise_intensity.toFloat()) }


                val bardataSet = BarDataSet(allExercise, "Workout Intensities Over Time").apply {
                    valueTextColor = Color.BLACK
                    color = ContextCompat.getColor(requireContext(), R.color.purple_700)
                }

                barChart.data = BarData(bardataSet)
            }
        })
    }


}