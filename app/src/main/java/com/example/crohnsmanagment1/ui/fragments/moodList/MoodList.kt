package com.example.crohnsmanagment1.ui.fragments.moodList

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
import com.example.crohnsmanagment1.data.models.Mood
import com.example.crohnsmanagment1.ui.fragments.moodList.adapters.MoodListAdapter
import com.example.crohnsmanagment1.ui.viewmodels.MoodViewModel
import kotlinx.android.synthetic.main.fragment_mood_list.fab_add
import kotlinx.android.synthetic.main.fragment_mood_list.swipeToRefresh
import kotlinx.android.synthetic.main.fragment_mood_list.tv_emptyView
import kotlinx.android.synthetic.main.fragment_mood_list.*


class MoodList : Fragment(R.layout.fragment_mood_list) {

    private lateinit var moodList: List<Mood>
    private lateinit var moodViewModel: MoodViewModel
    private lateinit var adapter: MoodListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Adapter
        adapter = MoodListAdapter()
        rv_mood.adapter = adapter
        rv_mood.layoutManager = LinearLayoutManager(context)

        //ViewModel
        moodViewModel = ViewModelProvider(this).get(MoodViewModel::class.java)

        moodViewModel.getAllMood.observe(viewLifecycleOwner, Observer {

            adapter.setData(it)
            moodList = it

            if (it.isEmpty()) {
                rv_mood.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            }else{
                rv_mood.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }

        })

        setHasOptionsMenu(true)

        swipeToRefresh.setOnRefreshListener {
            adapter.setData(moodList)
            swipeToRefresh.isRefreshing = false
        }


        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_moodList_to_addMood)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.nav_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_deleteEverything -> moodViewModel.deleteAll()
        }

        return super.onOptionsItemSelected(item)
    }





}