package com.example.crohnsmanagment1.ui.fragments.profile.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.Weight
import com.example.crohnsmanagment1.ui.fragments.profile.adapters.UserAdapter.*
import kotlinx.android.synthetic.main.recycler_weight_item.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

   var weightList = emptyList<Weight>()
   var TAG = "UserAdapter"

   inner class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

       init {
           itemView.cv_cardViewWeight.setOnClickListener {
               val position = adapterPosition
               Log.d(TAG, "Item clicked at: ${position}")
               Log.d(TAG,"ID: ${weightList[position].id}")

           }
       }
   }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_weight_item, parent, false))
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val currentWeight = weightList[position]

        //holder.itemView.iv_exercise
        holder.itemView.tv_currentWeight.text = currentWeight.weight_kg.toString()
        holder.itemView.tv_weightCardDate.text = currentWeight.weight_date


    }

    override fun getItemCount(): Int {
        return weightList.size
    }

    fun setData(weight : List<Weight>){
        this.weightList = weight
        notifyDataSetChanged()
    }
}