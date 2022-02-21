package com.example.crohnsmanagment1.ui.fragments.stoolList

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Food
import com.example.crohnsmanagment1.data.models.Stool
import com.example.crohnsmanagment1.ui.fragments.foodList.adapters.FoodListAdapter
import com.example.crohnsmanagment1.ui.fragments.stoolList.adapters.StoolListAdapter
import com.example.crohnsmanagment1.ui.viewmodels.FoodViewModel
import com.example.crohnsmanagment1.ui.viewmodels.StoolViewModel
import kotlinx.android.synthetic.main.fragment_food_list.*
import kotlinx.android.synthetic.main.fragment_food_list.fab_add
import kotlinx.android.synthetic.main.fragment_food_list.rv_food
import kotlinx.android.synthetic.main.fragment_food_list.swipeToRefresh
import kotlinx.android.synthetic.main.fragment_food_list.tv_emptyView
import kotlinx.android.synthetic.main.fragment_stool_list.*


class StoolList : Fragment(R.layout.fragment_stool_list) {

    private lateinit var stoolList: List<Stool>
    private lateinit var stoolViewModel: StoolViewModel
    private lateinit var adapter: StoolListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Adapter
        adapter = StoolListAdapter()
        rv_stool.adapter = adapter
        rv_stool.layoutManager = LinearLayoutManager(context)

        //ViewModel
        stoolViewModel = ViewModelProvider(this).get(StoolViewModel::class.java)

        stoolViewModel.getAllStool.observe(viewLifecycleOwner, Observer {

            adapter.setData(it)
            stoolList = it

            if (it.isEmpty()) {
                rv_stool.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            }else{
                rv_stool.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }

        })

        setHasOptionsMenu(true)

        swipeToRefresh.setOnRefreshListener {
            adapter.setData(stoolList)
            swipeToRefresh.isRefreshing = false
        }


        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_stoolList_to_addStool)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.nav_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_deleteEverything -> stoolViewModel.deleteAll()
        }

        return super.onOptionsItemSelected(item)
    }





}