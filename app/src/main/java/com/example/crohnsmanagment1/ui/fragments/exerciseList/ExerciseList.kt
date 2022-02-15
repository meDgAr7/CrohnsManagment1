package com.example.crohnsmanagment1.ui.fragments.exerciseList

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
import com.example.crohnsmanagment1.data.models.Exercise
import com.example.crohnsmanagment1.ui.fragments.exerciseList.adapters.ExerciseListAdapter

import com.example.crohnsmanagment1.ui.viewmodels.ExerciseViewModel
import kotlinx.android.synthetic.main.fragment_exercise_list.*


class ExerciseList : Fragment(R.layout.fragment_exercise_list) {

    private lateinit var exerciseList: List<Exercise>
    private lateinit var exerciseViewModel: ExerciseViewModel
    private lateinit var adapter: ExerciseListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Adapter
        adapter = ExerciseListAdapter()
        rv_exercise.adapter = adapter
        rv_exercise.layoutManager = LinearLayoutManager(context)

        //ViewModel
        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)

        exerciseViewModel.getAllExercise.observe(viewLifecycleOwner, Observer {

            adapter.setData(it)
            exerciseList = it

            if (it.isEmpty()) {
                rv_exercise.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            }else{
                rv_exercise.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }

        })

        setHasOptionsMenu(true)

        swipeToRefresh.setOnRefreshListener {
            adapter.setData(exerciseList)
            swipeToRefresh.isRefreshing = false
        }


        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_exerciseList_to_addExercise)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.nav_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_deleteEverything -> exerciseViewModel.deleteAll()
        }

        return super.onOptionsItemSelected(item)
    }





}