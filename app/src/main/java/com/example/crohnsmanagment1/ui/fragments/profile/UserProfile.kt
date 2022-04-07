package com.example.crohnsmanagment1.ui.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Weight
import com.example.crohnsmanagment1.ui.fragments.profile.adapters.UserAdapter
import com.example.crohnsmanagment1.ui.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_profile.*
import kotlinx.android.synthetic.main.fragment_user_profile.fab_addWeight

class UserProfile : Fragment(R.layout.fragment_user_profile) {






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Weight card and onClickListener navigates to weight stats
        cv_cardViewWeight.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_weightGraph)
        }

        //add weight button
        fab_addWeight.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_addWeight)
        }

        //Appointments Card, onClickListener to appointments fragment
        cv_cardViewAppointment.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_appointmentList)
        }

        //add appointment button
        fab_addAppointment.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_addAppointment)
        }

        //Medication Card
        cv_cardViewMedication.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_medicationList)
        }

        //add medication button
        fab_addMedication.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_addMedication)
        }

        //Notes Card/Notes list
        cv_cardViewNotes.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_notesList)
        }

        //Add notes button
        fab_addNotes.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_addNote)
        }

    }
}