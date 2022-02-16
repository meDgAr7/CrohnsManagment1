package com.example.crohnsmanagment1.ui.fragments.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.ui.fragments.foodList.FoodList
import kotlinx.android.synthetic.main.fragment_home_page.*


class HomePage : Fragment(R.layout.fragment_home_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        btn_foodLog.setOnClickListener{
            findNavController().navigate(R.id.action_homePage_to_foodList)
        }

        btn_exerciseLog.setOnClickListener{
            findNavController().navigate(R.id.action_homePage_to_exerciseList)
        }

        btn_moodLog.setOnClickListener{
            findNavController().navigate(R.id.action_homePage_to_moodList)
        }


    }

}