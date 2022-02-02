package com.example.crohnsmanagment1.ui.fragments.foodList.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Food
import com.example.crohnsmanagment1.logic.utils.Calculations
import com.example.crohnsmanagment1.ui.fragments.foodList.FoodListDirections
import kotlinx.android.synthetic.main.recycler_food_item.view.*

class FoodListAdapter : RecyclerView.Adapter<FoodListAdapter.MyViewHolder>() {

    var foodList = emptyList<Food>()
    val TAG = "FoodListAdapter"

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        init{
            itemView.cv_cardView.setOnClickListener {
                val position = adapterPosition
                Log.d(TAG, "Item clicked at: $position")
                Log.d(TAG, "ID: ${foodList[position].id}")

                val action = FoodListDirections.actionFoodListToUpdateFood(foodList[position])
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodListAdapter.MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_food_item, parent, false))
    }

    override fun onBindViewHolder(holder: FoodListAdapter.MyViewHolder, position: Int) {
       val currentFood = foodList[position]

        //holder.itemView.iv_food
        holder.itemView.tv_item_title.text = currentFood.food_title
        holder.itemView.tv_item_description.text = currentFood.food_description
        holder.itemView.tv_timeElapsed.text = Calculations().calculateTimeBetweenDates(currentFood.food_starTime)
        holder.itemView.tv_item_createdTimeStamp.text = "Since: ${currentFood.food_starTime}"

    }

    override fun getItemCount(): Int {
       return foodList.size
    }

    fun setData(food: List<Food>){
        this.foodList = food
        notifyDataSetChanged()

    }
}



