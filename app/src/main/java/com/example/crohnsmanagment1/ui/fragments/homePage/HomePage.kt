package com.example.crohnsmanagment1.ui.fragments.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.crohnsmanagment1.R
import kotlinx.android.synthetic.main.fragment_home_page.*


class HomePage : Fragment(R.layout.fragment_home_page), View.OnClickListener {

    var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.btn_foodLog).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_exerciseLog).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_moodLog).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_stoolLog).setOnClickListener(this)



    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_foodLog -> navController!!.navigate(R.id.action_homePage_to_foodList)
            R.id.btn_exerciseLog -> navController!!.navigate(R.id.action_homePage_to_exerciseList)
            R.id.btn_moodLog -> navController!!.navigate(R.id.action_homePage_to_moodList)
            R.id.btn_stoolLog -> navController!!.navigate(R.id.action_homePage_to_stoolList)

        }
    }

}