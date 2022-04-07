package com.example.crohnsmanagment1.ui.fragments.addExercise

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
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
import kotlinx.android.synthetic.main.fragment_add_mood.*
import java.text.SimpleDateFormat
import java.util.*


class AddExercise : Fragment(R.layout.fragment_add_exercise),
    TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, SeekBar.OnSeekBarChangeListener {

    private var title = ""
    private var notes = ""
    private var intensity = 0
    private var intensity_start : TextView? = null
    private var intensity_end : TextView? = null
    private var duration = Float.NaN
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

         intensity_end = this.tv_intensity

        this.intensity_seek!!.setOnSeekBarChangeListener(this)
        intensity_seek.setMax(10)

        btn_confirm.setOnClickListener{
            addExerciseToDB()
        }

        pickDateAndTime()

        setDuration()

        // drawableSelected()

    }

    private fun addExerciseToDB(){
        title = et_exerciseTitle.text.toString()
        notes = et_exerciseDescription.text.toString()

        timeStamp = "$cleanDate $cleanTime"

        if (!(title.isEmpty() ||   timeStamp.isEmpty())) {
            val exercise = Exercise(0, intensity, duration, title, notes, timeStamp)

            exerciseViewModel.addExercise(exercise)
            Toast.makeText(context, "Exercise Added Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addExercise_to_exerciseList)

        }else{
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    //  private fun drawableSelected(){

    // }

    private fun setDuration(){
        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            tv_durationSet.text = SimpleDateFormat("HH:mm").format(cal.time)
        }

        tv_durationSet.setOnClickListener {
            TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
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

    override fun onProgressChanged(seek: SeekBar?, progress: Int, fromUser: Boolean) {
       intensity_end!!.text = progress.toString()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(seek: SeekBar?) {
        intensity = intensity_end!!.text.toString().toInt()
    }


}