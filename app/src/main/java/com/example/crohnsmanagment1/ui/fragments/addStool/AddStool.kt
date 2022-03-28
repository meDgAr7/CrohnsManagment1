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
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_add_food.*
import kotlinx.android.synthetic.main.fragment_add_stool.btn_confirm
import kotlinx.android.synthetic.main.fragment_add_stool.btn_pickDate
import kotlinx.android.synthetic.main.fragment_add_stool.btn_pickTime
import kotlinx.android.synthetic.main.fragment_add_stool.tv_dateSelected
import kotlinx.android.synthetic.main.fragment_add_stool.tv_timeSelected
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

        tv_dateSelected.setText(currentDate)
        tv_timeSelected.setText(currentTime)

        btn_confirm.setOnClickListener{
            addStoolToDB()
        }


        pickDateAndTime()

        chipSelectedType()

        chipSelectedBlood()

        // drawableSelected()

    }

    private fun addStoolToDB(){

        description = et_stoolDescription.text.toString()

        type = chipGroup.isSingleSelection.toString()
        blood = chipGroup1.isSingleSelection.toString()



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
    private fun chipSelectedType(){

        when(chipGroup){
            chip_1 -> type = "Type 1"
            chip_2 -> type = "Type 2"
            chip_3 -> type = "Type 3"
            chip_4 -> type = "Type 4"
            chip_5 -> type = "Type 5"
            chip_6 -> type = "Type 6"
            chip_7 -> type = "Type 7"
        }
    }

    //select blood option
    private fun chipSelectedBlood(){

        when(chipGroup1){
            chip_b1 -> blood = "None"
            chip_b2 -> blood = "Visible"
            chip_b3 -> blood = "Just Blood"
        }

    }


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