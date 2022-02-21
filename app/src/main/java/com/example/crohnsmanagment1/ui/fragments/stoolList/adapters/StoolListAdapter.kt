package com.example.crohnsmanagment1.ui.fragments.stoolList.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Stool
import com.example.crohnsmanagment1.logic.utils.Calculations
import com.example.crohnsmanagment1.ui.fragments.stoolList.StoolListDirections
import kotlinx.android.synthetic.main.recycler_food_item.view.*

class StoolListAdapter : RecyclerView.Adapter<StoolListAdapter.MyViewHolder>() {

    var stoolList = emptyList<Stool>()
    val TAG = "StoolListAdapter"

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        init{
            itemView.cv_cardView.setOnClickListener {
                val position = adapterPosition
                Log.d(TAG, "Item clicked at: $position")
                Log.d(TAG, "ID: ${stoolList[position].id}")


                val action = StoolListDirections.actionStoolListToUpdateStool(stoolList[position])
                itemView.findNavController().navigate(action)


            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoolListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_stool_item, parent, false))
    }

    override fun onBindViewHolder(holder: StoolListAdapter.MyViewHolder, position: Int) {
        val currentStool = stoolList[position]

        //holder.itemView.iv_stool
        holder.itemView.tv_item_title.text = currentStool.stool_title
        holder.itemView.tv_item_description.text = currentStool.stool_description
        holder.itemView.tv_timeElapsed.text = Calculations().calculateTimeBetweenDates(currentStool.stool_starTime)
        holder.itemView.tv_item_createdTimeStamp.text = "Added on: ${currentStool.stool_starTime}"

    }

    override fun getItemCount(): Int {
        return stoolList.size
    }

    fun setData(stool: List<Stool>){
        this.stoolList = stool
        notifyDataSetChanged()

    }
}
