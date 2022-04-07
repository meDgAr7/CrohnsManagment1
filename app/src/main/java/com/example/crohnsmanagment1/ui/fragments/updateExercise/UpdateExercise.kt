package com.example.crohnsmanagment1.ui.fragments.updateExercise

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Exercise
import com.example.crohnsmanagment1.logic.utils.Calculations
import com.example.crohnsmanagment1.ui.viewmodels.ExerciseViewModel
import kotlinx.android.synthetic.main.fragment_add_exercise.*
import kotlinx.android.synthetic.main.fragment_update_exercise.*
import kotlinx.android.synthetic.main.fragment_update_exercise.btn_confirm_update
import kotlinx.android.synthetic.main.fragment_update_exercise.btn_pickDate_update
import kotlinx.android.synthetic.main.fragment_update_exercise.btn_pickTime_update
import kotlinx.android.synthetic.main.fragment_update_exercise.tv_dateSelected_update
import kotlinx.android.synthetic.main.fragment_update_exercise.tv_timeSelected_update
import java.text.SimpleDateFormat
import java.util.*


class UpdateExercise : Fragment(R.layout.fragment_update_exercise),
    TimePickerDialog.OnTimeSetListener ,DatePickerDialog.OnDateSetListener, SeekBar.OnSeekBarChangeListener {

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
    private val args by navArgs<UpdateExerciseArgs>()

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {


        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)

        val currentDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        tv_dateSelected.setText(currentDate)
        tv_timeSelected.setText(currentTime)

        et_exerciseTitle_update.setText(args.selectedExercise.exercise_title)
        et_exerciseDescription_update.setText(args.selectedExercise.exercise_description)

        // drawableSelected() add later

        pickDateAndTime()

        btn_confirm_update.setOnClickListener{
            updateExercise()
        }

        setHasOptionsMenu(true)


    }

    private fun updateExercise(){
        title = et_exerciseTitle_update.text.toString()
        notes = et_exerciseDescription_update.text.toString()

        timeStamp = "$cleanDate $cleanTime"

        if (!(title.isEmpty() || notes.isEmpty() || timeStamp.isEmpty())){
            val exercise = Exercise(args.selectedExercise.id, intensity, duration, title, notes, timeStamp)

            exerciseViewModel.updateExercise(exercise)
            Toast.makeText(context, "Exercise updated successfully!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateExercise_to_exerciseList)
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
            R.id.nav_delete -> deleteExercise(args.selectedExercise)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteExercise(exercise: Exercise){
        exerciseViewModel.deleteExercise(exercise)
        Toast.makeText(context, "Exercise Successfully deleted!", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_updateExercise_to_exerciseList)
    }

    override fun onProgressChanged(seek: SeekBar?, progress: Int, fromUser: Boolean) {
        intensity_end!!.text = progress.toString()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        intensity_start!!.text.toString()

    }

    override fun onStopTrackingTouch(seek: SeekBar?) {
        intensity = intensity_end!!.text.toString().toInt()
    }

}
