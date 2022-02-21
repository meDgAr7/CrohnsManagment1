package com.example.crohnsmanagment1.ui.fragments.addStool

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Stool
import com.example.crohnsmanagment1.logic.utils.Calculations
import com.example.crohnsmanagment1.ui.viewmodels.StoolViewModel
import kotlinx.android.synthetic.main.fragment_add_stool.btn_confirm
import kotlinx.android.synthetic.main.fragment_add_stool.btn_pickDate
import kotlinx.android.synthetic.main.fragment_add_stool.btn_pickTime
import kotlinx.android.synthetic.main.fragment_add_stool.tv_dateSelected
import kotlinx.android.synthetic.main.fragment_add_stool.tv_timeSelected
import kotlinx.android.synthetic.main.fragment_add_stool.*
import java.util.*


class AddStool : Fragment(R.layout.fragment_add_stool),
    TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var title = ""
    private var description = ""
    private var drawableSelected = 0
    private var timeStamp = ""

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

        btn_confirm.setOnClickListener{
            addStoolToDB()
        }

        pickDateAndTime()

        // drawableSelected()

    }

    private fun addStoolToDB(){
        title = et_stoolTitle.text.toString()
        description = et_stoolDescription.text.toString()

        timeStamp = "$cleanDate $cleanTime"

        if (!(title.isEmpty() || description.isEmpty() || timeStamp.isEmpty())) {
            val stool = Stool(0, title, description, timeStamp)

            stoolViewModel.addStool(stool)
            Toast.makeText(context, "Stool Added Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addStool_to_stoolList)

        }else{
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    //  private fun drawableSelected(){

    // }


    private fun pickDateAndTime(){
        btn_pickDate.setOnClickListener{
            getDateCalendar()
            DatePickerDialog(requireContext(),this, year, month, day).show()
        }

        btn_pickTime.setOnClickListener{
            getTimeCalendar()
            TimePickerDialog(context, this, hour, minute,true).show()
        }
    }



    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

        cleanTime = Calculations().cleanTime(p1, p2)
        tv_timeSelected.text = "Time: $cleanTime"
    }

    override fun onDateSet(p0: DatePicker?, yearX: Int, monthX: Int, dayX: Int) {
        cleanDate = Calculations().cleanDate(dayX, monthX, yearX)
        tv_dateSelected.text = "Date: $cleanDate"
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