package com.example.crohnsmanagment1.ui.fragments.foodList

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
import com.example.crohnsmanagment1.ui.fragments.foodList.adapters.FoodListAdapter
import com.example.crohnsmanagment1.ui.viewmodels.FoodViewModel
import kotlinx.android.synthetic.main.fragment_food_list.*


class FoodList : Fragment(R.layout.fragment_food_list) {

    private lateinit var foodList: List<Food>
    private lateinit var foodViewModel: FoodViewModel
    private lateinit var adapter: FoodListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Adapter
        adapter = FoodListAdapter()
        rv_food.adapter = adapter
        rv_food.layoutManager = LinearLayoutManager(context)

        //ViewModel
        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)

        foodViewModel.getAllFood.observe(viewLifecycleOwner, Observer {

            adapter.setData(it)
            foodList = it

            if (it.isEmpty()) {
                rv_food.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            }else{
                rv_food.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }

        })

        setHasOptionsMenu(true)

        swipeToRefresh.setOnRefreshListener {
            adapter.setData(foodList)
            swipeToRefresh.isRefreshing = false
        }


        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_foodList_to_addFood)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.nav_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_deleteEverything -> foodViewModel.deleteAll()
        }

        return super.onOptionsItemSelected(item)
    }





}