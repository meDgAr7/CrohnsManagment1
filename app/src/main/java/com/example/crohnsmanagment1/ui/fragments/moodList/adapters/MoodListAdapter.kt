package com.example.crohnsmanagment1.ui.fragments.moodList.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crohnsmanagment1.MoodListDirections
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Mood
import com.example.crohnsmanagment1.logic.utils.Calculations
import kotlinx.android.synthetic.main.recycler_food_item.view.*

class MoodListAdapter : RecyclerView.Adapter<MoodListAdapter.MyViewHolder>() {

    var moodList = emptyList<Mood>()
    val TAG = "MoodListAdapter"

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        init{
            itemView.cv_cardView.setOnClickListener {
                val position = adapterPosition
                Log.d(TAG, "Item clicked at: $position")
                Log.d(TAG, "ID: ${moodList[position].id}")

                val action = MoodListDirections.actionMoodListToUpdateMood(moodList[position])
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoodListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_food_item, parent, false))
    }

    override fun onBindViewHolder(holder: MoodListAdapter.MyViewHolder, position: Int) {
        val currentMood = moodList[position]

        //holder.itemView.iv_mood
        holder.itemView.tv_item_title.text = currentMood.mood_title
        holder.itemView.tv_item_description.text = currentMood.mood_description
        holder.itemView.tv_timeElapsed.text = Calculations().calculateTimeBetweenDates(currentMood.mood_starTime)
        holder.itemView.tv_item_createdTimeStamp.text = "Since: ${currentMood.mood_starTime}"

    }

    override fun getItemCount(): Int {
        return moodList.size
    }

    fun setData(mood: List<Mood>){
        this.moodList = mood
        notifyDataSetChanged()

    }
}
