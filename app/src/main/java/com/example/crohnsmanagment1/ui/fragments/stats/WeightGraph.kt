package com.example.crohnsmanagment1.ui.fragments.stats

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Weight
import com.example.crohnsmanagment1.ui.fragments.profile.adapters.UserAdapter
import com.example.crohnsmanagment1.ui.viewmodels.UserViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.fragment_mood_graph.*
import kotlinx.android.synthetic.main.fragment_user_profile.tv_emptyView
import kotlinx.android.synthetic.main.fragment_weight_graph.*
import kotlinx.android.synthetic.main.fragment_weight_graph.barChart


class WeightGraph : Fragment(R.layout.fragment_weight_graph) {

    private lateinit var weightList: List<Weight>
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = UserAdapter()
        rv_weight.adapter = adapter
        rv_weight.layoutManager = LinearLayoutManager(context)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //chart setup method
        setUpBarChart()

        //populate chart method
        populateBarChart()

        //populate weight cardViews
       userViewModel.getAllWeight.observe(viewLifecycleOwner, Observer {


           adapter.setData(it)
           weightList = it

           if (it.isEmpty()){
               rv_weight.visibility = View.GONE
               tv_emptyView.visibility = View.VISIBLE
           }else{
               rv_weight.visibility = View.VISIBLE
               tv_emptyViewWeight.visibility = View.GONE
           }
       })

        setHasOptionsMenu(true)

        swipeToRefreshWeight.setOnRefreshListener {
            adapter.setData(weightList)
            swipeToRefreshWeight.isRefreshing = false
        }

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
            description.text = "Weight Over Time"
            legend.isEnabled = false
        }
    }

    private fun populateBarChart(){

        userViewModel.getAllWeight.observe(viewLifecycleOwner, Observer{
            it?.let{
                val allWeight = it.indices.map{ i -> BarEntry(i.toFloat(), it[i].weight_kg)}

                val bardataSet = BarDataSet(allWeight, "Weight over time").apply {
                    valueTextColor = Color.BLACK
                    color = ContextCompat.getColor(requireContext(), R.color.purple_500)
                }

                barChart.data = BarData(bardataSet)
            }
        })
    }


    //toolbar menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.nav_main, menu)
    }

    //delete all weight data
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_deleteEverything -> userViewModel.deleteAll()
        }

        return super.onOptionsItemSelected(item)
    }

}