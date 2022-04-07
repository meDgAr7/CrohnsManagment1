package com.example.crohnsmanagment1.ui.fragments.profile

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Weight
import com.example.crohnsmanagment1.logic.utils.Calculations
import com.example.crohnsmanagment1.ui.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_food.*
import kotlinx.android.synthetic.main.fragment_add_weight.*
import java.text.SimpleDateFormat
import java.util.*


class AddWeight : Fragment(R.layout.fragment_add_weight), DatePickerDialog.OnDateSetListener {

    private var weight = Float.NaN
    private var date = ""

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""


    private lateinit var userViewModel : UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val currentDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        tv_weightDateSelect.setText(currentDate)

        btn_saveWeight.setOnClickListener{
            addWeightToDB()
        }

        pickDate()
    }

    private fun addWeightToDB(){
        weight = et_weightSet.text.toString().toFloat()

        date = "$cleanDate"

        if(!(weight.isNaN() || date.isEmpty())){
            val weight = Weight(0, weight, date)

            userViewModel.addWeight(weight)
            Toast.makeText(context, "Weight Updated Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addWeight_to_profileFragment)
        }else{
            Toast.makeText(context, "unsuccessful", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pickDate(){

        tv_weightDateSelect.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(),this, year, month, day).show()
        }
    }

    override fun onDateSet(p0: DatePicker?,  yearX: Int, monthX: Int, dayX: Int) {

        cleanDate = Calculations().cleanDate(dayX, monthX, yearX)
        tv_weightDateSelect.text = "Date: $cleanDate"
    }

    private fun getDateCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

}