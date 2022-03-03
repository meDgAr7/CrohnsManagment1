package com.example.crohnsmanagment1.ui.fragments.updateStool

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Food
import com.example.crohnsmanagment1.data.models.Stool
import com.example.crohnsmanagment1.logic.utils.Calculations
import com.example.crohnsmanagment1.ui.viewmodels.FoodViewModel
import com.example.crohnsmanagment1.ui.viewmodels.StoolViewModel
import kotlinx.android.synthetic.main.fragment_add_food.*
import kotlinx.android.synthetic.main.fragment_update_food.*
import kotlinx.android.synthetic.main.fragment_update_food.btn_confirm_update
import kotlinx.android.synthetic.main.fragment_update_food.btn_pickDate_update
import kotlinx.android.synthetic.main.fragment_update_food.btn_pickTime_update
import kotlinx.android.synthetic.main.fragment_update_food.tv_dateSelected_update
import kotlinx.android.synthetic.main.fragment_update_food.tv_timeSelected_update
import kotlinx.android.synthetic.main.fragment_update_stool.*
import java.util.*


class UpdateStool : Fragment(R.layout.fragment_update_stool),
    TimePickerDialog.OnTimeSetListener ,DatePickerDialog.OnDateSetListener {

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
    private val args by navArgs<UpdateStoolArgs>()

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {


        stoolViewModel = ViewModelProvider(this).get(StoolViewModel::class.java)

        //et_stoolTitle_update.setText(args.selectedStool.stool_title)
        et_stoolDescription_update.setText(args.selectedStool.stool_description)

        // drawableSelected() add later

        pickDateAndTime()

        btn_confirm_update.setOnClickListener{
            updateStool()
        }

        setHasOptionsMenu(true)


    }

    private fun updateStool(){
        title = et_stoolTitle_update.text.toString()
        description = et_stoolDescription_update.text.toString()

        timeStamp = "$cleanDate $cleanTime"

        if (!(title.isEmpty() || description.isEmpty() || timeStamp.isEmpty())){
            val stool = Stool(args.selectedStool.id, description, timeStamp)

            stoolViewModel.updateStool(stool)
            Toast.makeText(context, "Stool updated successfully!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateStool_to_stoolList)
        }else {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }

    }


    //private fun drawableSelected() add later

    private fun pickDateAndTime(){
        btn_pickDate_update.setOnClickListener{
            getDateCalendar()
            DatePickerDialog(requireContext(),this, year, month, day).show()
        }

        btn_pickTime_update.setOnClickListener{
            getTimeCalendar()
            TimePickerDialog(context, this, hour, minute,true).show()
        }
    }


    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

        cleanTime = Calculations().cleanTime(p1, p2)
        tv_timeSelected_update.text = "Time: $cleanTime"
    }

    override fun onDateSet(p0: DatePicker?, yearX: Int, monthX: Int, dayX: Int) {
        cleanDate = Calculations().cleanDate(dayX, monthX, yearX)
        tv_dateSelected_update.text = "Date: $cleanDate"
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.single_item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_delete -> deleteStool(args.selectedStool)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteStool(stool: Stool){
        stoolViewModel.deleteStool(stool)
        Toast.makeText(context, "Stool Successfully deleted!", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_updateStool_to_stoolList)
    }

}