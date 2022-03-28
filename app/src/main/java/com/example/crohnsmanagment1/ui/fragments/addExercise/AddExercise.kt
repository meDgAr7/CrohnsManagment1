package com.example.crohnsmanagment1.ui.fragments.addExercise

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
import com.example.crohnsmanagment1.data.models.Exercise
import com.example.crohnsmanagment1.logic.utils.Calculations
import com.example.crohnsmanagment1.ui.viewmodels.ExerciseViewModel
import kotlinx.android.synthetic.main.fragment_add_exercise.*
import kotlinx.android.synthetic.main.fragment_add_exercise.btn_confirm
import kotlinx.android.synthetic.main.fragment_add_exercise.btn_pickDate
import kotlinx.android.synthetic.main.fragment_add_exercise.btn_pickTime
import kotlinx.android.synthetic.main.fragment_add_exercise.tv_dateSelected
import kotlinx.android.synthetic.main.fragment_add_exercise.tv_timeSelected
import kotlinx.android.synthetic.main.fragment_add_food.*
import java.text.SimpleDateFormat
import java.util.*


class AddExercise : Fragment(R.layout.fragment_add_exercise),
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

    private lateinit var exerciseViewModel: ExerciseViewModel



    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel:: class.java)

        val currentDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        tv_dateSelected.setText(currentDate)
        tv_timeSelected.setText(currentTime)

        btn_confirm.setOnClickListener{
            addExerciseToDB()
        }

        pickDateAndTime()

        // drawableSelected()

    }

    private fun addExerciseToDB(){
        title = et_exerciseTitle.text.toString()
        description = et_exerciseDescription.text.toString()

        timeStamp = "$cleanDate $cleanTime"

        if (!(title.isEmpty() || description.isEmpty() || timeStamp.isEmpty())) {
            val exercise = Exercise(0, title, description, timeStamp)

            exerciseViewModel.addExercise(exercise)
            Toast.makeText(context, "Exercise Added Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addExercise_to_exerciseList)

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