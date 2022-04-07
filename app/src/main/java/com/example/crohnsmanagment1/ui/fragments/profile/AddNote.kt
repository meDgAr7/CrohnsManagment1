package com.example.crohnsmanagment1.ui.fragments.profile

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Notes
import com.example.crohnsmanagment1.logic.utils.Calculations
import com.example.crohnsmanagment1.ui.viewmodels.NotesViewModel
import kotlinx.android.synthetic.main.fragment_add_food.*
import kotlinx.android.synthetic.main.fragment_add_note.*
import java.util.*


class AddNote : Fragment(R.layout.fragment_add_note),
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private var title = ""
    private var body = ""
    private var timeStamp = ""

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""
    private var cleanTime = ""

    private lateinit var notesViewModel : NotesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        btn_confirmNotes.setOnClickListener{
            addNotesToDB()
        }

        pickDateAndTime()
    }


    private fun addNotesToDB(){
        title = et_notesTitle.text.toString()
        body = et_notesBody.text.toString()

        timeStamp = "$cleanDate $cleanTime"

        if (!(title.isEmpty() || body.isEmpty() || timeStamp.isEmpty())){
            val notes = Notes(0, title, body, timeStamp)

            notesViewModel.addNotes(notes)
            Toast.makeText(context, "Note Recorded Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addNote_to_notesList)

        }else{
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun pickDateAndTime(){
        tv_timeSelectedNotes.setOnClickListener{
            getDateCalendar()
            DatePickerDialog(requireContext(),this, year, month, day).show()
        }

        tv_dateSelectedNotes.setOnClickListener{
            getTimeCalendar()
            TimePickerDialog(context, this, hour, minute,true).show()
        }
    }


    override fun onDateSet(p0: DatePicker?, dayX: Int, monthX: Int, yearX: Int) {
        cleanDate = Calculations().cleanDate(dayX, monthX, yearX)
        tv_dateSelectedNotes.text = "Date: $cleanDate"
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        cleanTime = Calculations().cleanTime(p1, p2)
        tv_timeSelectedNotes.text = "Time: $cleanTime"
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