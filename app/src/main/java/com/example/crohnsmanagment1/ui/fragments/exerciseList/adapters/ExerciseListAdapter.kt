package com.example.crohnsmanagment1.ui.fragments.exerciseList.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crohnsmanagment1.ui.fragments.exerciseList.ExerciseListDirections
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Exercise
import com.example.crohnsmanagment1.logic.utils.Calculations
import kotlinx.android.synthetic.main.recycler_exercise_item.view.*

class ExerciseListAdapter : RecyclerView.Adapter<ExerciseListAdapter.MyViewHolder1>() {

    var exerciseList = emptyList<Exercise>()
    val TAG = "ExerciseListAdapter"

    inner class MyViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView){

        init{
            itemView.cv_cardView.setOnClickListener {
                val position = adapterPosition
                Log.d(TAG, "Item clicked at: $position")
                Log.d(TAG, "ID: ${exerciseList[position].id}")

                val action = ExerciseListDirections.actionExerciseListToUpdateExercise(exerciseList[position])
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseListAdapter.MyViewHolder1 {
        return MyViewHolder1(LayoutInflater.from(parent.context).inflate(R.layout.recycler_exercise_item, parent, false))
    }

    override fun onBindViewHolder(holder: ExerciseListAdapter.MyViewHolder1, position: Int) {
        val currentExercise = exerciseList[position]

        //holder.itemView.iv_exercise
        holder.itemView.tv_item_title.text = currentExercise.exercise_title
        holder.itemView.tv_item_description.text = currentExercise.exercise_description
        holder.itemView.tv_timeElapsed.text = Calculations().calculateTimeBetweenDates(currentExercise.exercise_starTime)
        holder.itemView.tv_item_createdTimeStamp.text = "Added on: ${currentExercise.exercise_starTime}"

    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    fun setData(exercise: List<Exercise>){
        this.exerciseList = exercise
        notifyDataSetChanged()

    }
}
