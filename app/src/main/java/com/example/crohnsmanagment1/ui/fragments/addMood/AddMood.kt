package com.example.crohnsmanagment1.ui.fragments.addMood

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import android.widget.SeekBar.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Mood
import com.example.crohnsmanagment1.logic.utils.Calculations
import com.example.crohnsmanagment1.ui.viewmodels.MoodViewModel
import kotlinx.android.synthetic.main.fragment_add_mood.*
import kotlinx.android.synthetic.main.fragment_add_mood.btn_confirm
import kotlinx.android.synthetic.main.fragment_add_mood.btn_pickDate
import kotlinx.android.synthetic.main.fragment_add_mood.btn_pickTime
import kotlinx.android.synthetic.main.fragment_add_mood.tv_dateSelected
import kotlinx.android.synthetic.main.fragment_add_mood.tv_timeSelected
import kotlinx.android.synthetic.main.fragment_add_mood.*
import java.util.*
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.fragment_add_food.*
import java.text.SimpleDateFormat


class AddMood : Fragment(R.layout.fragment_add_mood),
    TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, OnSeekBarChangeListener{

    private var rating  = 0
    private var ratingStart : TextView? = null
    private var ratingEnd : TextView? = null
    private var stress  = 0
    private var stressStart : TextView? = null
    private var stressEnd : TextView? = null
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

    private lateinit var moodViewModel: MoodViewModel



    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        moodViewModel = ViewModelProvider(this).get(MoodViewModel:: class.java)

        val currentDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        tv_dateSelected.setText(currentDate)
        tv_timeSelected.setText(currentTime)

        ratingEnd = this.tv_mood_rating

        stressEnd = this.tv_stress_rating

        btn_confirm.setOnClickListener{
            addMoodToDB()
        }

        pickDateAndTime()


        this.mood_seek!!.setOnSeekBarChangeListener(this)
        mood_seek.setMax(5)

        this.stress_seek!!.setOnSeekBarChangeListener(this)
        stress_seek.setMax(10)

        // drawableSelected()

    }

    private fun addMoodToDB(){

        description = et_moodDescription.text.toString()
        //rating = ratingEnd.toString().toInt()
        //stress = stressEnd.

        timeStamp = "$cleanDate $cleanTime"

        if (!( description.isEmpty() || timeStamp.isEmpty())) {
            val mood = Mood(0, rating,stress, description, timeStamp)

            moodViewModel.addMood(mood)
            Toast.makeText(context, "Mood Added Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addMood_to_moodList)

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


    //Set values for mood rating & stress levels
    override fun onProgressChanged(seek: SeekBar?, progress: Int, fromUser: Boolean) {
        if(seek?.equals(mood_seek) == true) {
            ratingEnd!!.text = progress.toString()
        }else if(seek?.equals(stress_seek) == true){
            stressEnd!!.text = progress.toString()
        }

    }

    override fun onStartTrackingTouch(seek: SeekBar) {
        if(seek?.equals(mood_seek) == true){

        }else if(seek?.equals(stress_seek) == true){

        }
    }

    override fun onStopTrackingTouch(seek: SeekBar) {

        if(seek?.equals(mood_seek) == true){
            rating = ratingEnd!!.text.toString().toInt()

        }else if(seek?.equals(stress_seek) == true){

            stress = stressEnd!!.text.toString().toInt()
        }
    }





}


