package com.example.crohnsmanagment1.ui.fragments.foodList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.crohnsmanagment1.R
import kotlinx.android.synthetic.main.fragment_food_list.*


class FoodList : Fragment(R.layout.fragment_food_list) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_foodList_to_addFood)

        }
    }




}