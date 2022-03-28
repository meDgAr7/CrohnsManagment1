package com.example.crohnsmanagment1.ui.fragments.updateMood

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
import com.example.crohnsmanagment1.data.models.Mood
import com.example.crohnsmanagment1.logic.utils.Calculations
import com.example.crohnsmanagment1.ui.viewmodels.MoodViewModel
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.fragment_add_mood.*
import kotlinx.android.synthetic.main.fragment_update_mood.btn_confirm_update
import kotlinx.android.synthetic.main.fragment_update_mood.btn_pickDate_update
import kotlinx.android.synthetic.main.fragment_update_mood.btn_pickTime_update
import kotlinx.android.synthetic.main.fragment_update_mood.tv_dateSelected_update
import kotlinx.android.synthetic.main.fragment_update_mood.tv_timeSelected_update
import kotlinx.android.synthetic.main.fragment_update_mood.*
import java.text.SimpleDateFormat
import java.util.*


class UpdateMood : Fragment(R.layout.fragment_update_mood),
    TimePickerDialog.OnTimeSetListener ,DatePickerDialog.OnDateSetListener, Slider.OnSliderTouchListener {

    private var rating = 0
    private var ratingStart = 0
    private var ratingEnd = 0
    private var stress = 0
    private var stressStart = 0
    private var stressEnd = 0
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
    private val args by navArgs<UpdateMoodArgs>()

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {


        moodViewModel = ViewModelProvider(this).get(MoodViewModel::class.java)

        val currentDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        tv_dateSelected_update.setText(currentDate)
        tv_timeSelected_update.setText(currentTime)

        //et_moodTitle_update.setText(args.selectedMood.mood_title)
        et_moodDescription_update.setText(args.selectedMood.mood_description)
        tv_dateSelected_update.setText(args.selectedMood.mood_starTime)
        tv_timeSelected_update.setText(args.selectedMood.mood_starTime)

        // drawableSelected() add later

        pickDateAndTime()

        btn_confirm_update.setOnClickListener{
            updateMood()
        }

        setHasOptionsMenu(true)


    }

    private fun updateMood(){
        description = et_moodDescription_update.text.toString()
        rating = ratingEnd.toString().toInt()
        stress = stressEnd.toString().toInt()

        timeStamp = "$cleanDate $cleanTime"

        if (!( description.isEmpty() || timeStamp.isEmpty())) {
            val mood = Mood(0, rating, stress, description, timeStamp)
            moodViewModel.updateMood(mood)
            Toast.makeText(context, "Mood updated successfully!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateMood_to_moodList)
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

    override fun onStartTrackingTouch(slider: Slider) {

        ratingStart = slider.value.toInt()

    }

    override fun onStopTrackingTouch(slider: Slider) {
        ratingEnd = slider.value.toInt()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.single_item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_delete -> deleteMood(args.selectedMood)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteMood(mood: Mood){
        moodViewModel.deleteMood(mood)
        Toast.makeText(context, "Mood Successfully deleted!", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_updateMood_to_moodList)
    }

}
