package com.example.crohnsmanagment1.ui.fragments.addStool

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.view.ViewCompat.setAutofillHints
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Stool
import com.example.crohnsmanagment1.logic.utils.Calculations
import com.example.crohnsmanagment1.ui.viewmodels.StoolViewModel
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_add_food.*
import kotlinx.android.synthetic.main.fragment_add_stool.btn_confirm


import kotlinx.android.synthetic.main.fragment_add_stool.*
import java.text.SimpleDateFormat
import java.util.*


class AddStool : Fragment(R.layout.fragment_add_stool),
    TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {


    private var description = ""
    private var drawableSelected = 0
    private var timeStamp = ""
    private var blood = ""
    private var type = ""

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""
    private var cleanTime = ""

    private lateinit var stoolViewModel: StoolViewModel



    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        stoolViewModel = ViewModelProvider(this).get(StoolViewModel:: class.java)

        val currentDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        tv_dateSelectedStool.setText("Date: ${currentDate}")
        tv_timeSelectedStool.setText("Time: ${currentTime}")



        btn_confirm.setOnClickListener{
            addStoolToDB()
        }


        pickDateAndTime()

        getStoolType()

        getStoolBlood()

        // drawableSelected()

    }

    private fun addStoolToDB(){

        description = et_stoolDescription.text.toString()

        //type = tv_type.text.toString()
        //blood = tv_blood.text.toString()



        timeStamp = "$cleanDate $cleanTime"

        if (!(description.isEmpty() ||blood.isEmpty() || type.isEmpty() || timeStamp.isEmpty())) {
            val stool = Stool(0, description, timeStamp, type, blood)

            stoolViewModel.addStool(stool)
            Toast.makeText(context, "Stool Added Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addStool_to_stoolList)

        }else{
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    //  private fun drawableSelected(){

    // }


    //select stool type

    private fun getStoolType(){

        rg_StoolType.setOnCheckedChangeListener { rg_StoolType, checkId ->
            if(checkId == R.id.rbType1){
                type = "Type 1"

            }
            if(checkId == R.id.rbType2) {
                type = "Type 2"

            }
            if(checkId == R.id.rbType3){
                type = "Type 3"
            }
            if(checkId == R.id.rbType4){
                type = "Type 4"

            }
            if(checkId == R.id.rbType5){
                type = "Type 5"

            }
            if(checkId == R.id.rbType6){
                type = "Type 6"

            }
            if(checkId == R.id.rbType7){
                type = "Type 7"

            }
        }
    }

    private fun getStoolBlood(){
        rg_Blood.setOnCheckedChangeListener { rg_Blood, checkId ->
            if(checkId == R.id.rbBlood1){
                blood = "No Blood"

            }
            if(checkId == R.id.rbBlood2){

                blood = "Visible Blood"
            }
            if(checkId == R.id.rbBlood3){
                blood = "Just Blood"
            }
        }
    }


    private fun pickDateAndTime(){
        tv_dateSelectedStool.setOnClickListener{
            getDateCalendar()
            DatePickerDialog(requireContext(),this, year, month, day).show()
        }

        tv_timeSelectedStool.setOnClickListener{
           getTimeCalendar()
            TimePickerDialog(context, this, hour, minute,true).show()
        }
    }



    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

        cleanTime = Calculations().cleanTime(p1, p2)
        tv_timeSelectedStool.text = "Time: $cleanTime"
    }

    override fun onDateSet(p0: DatePicker?, yearX: Int, monthX: Int, dayX: Int) {
        cleanDate = Calculations().cleanDate(dayX, monthX, yearX)
        tv_dateSelectedStool.text = "Date: $cleanDate"
    }

    private fun getTimeCalendar(){
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)

    }

    private fun getDateCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }



}