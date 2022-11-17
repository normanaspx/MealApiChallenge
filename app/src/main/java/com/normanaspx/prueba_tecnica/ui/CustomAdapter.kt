package com.normanaspx.prueba_tecnica.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.normanaspx.prueba_tecnica.R
import com.normanaspx.prueba_tecnica.ext.ImageViewExt.toGlide
import com.normanaspx.prueba_tecnica.model.Meal


/**
Created by Norman on 11/16/2022
 **/
class CustomAdapter(private var itemsList: List<Meal>, val ctx: Context?) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View, val ctx: Context) :RecyclerView.ViewHolder(ItemView){
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        private val tvLink: TextView = itemView.findViewById(R.id.tvLink)
        private val ivMeal: ImageView =   itemView.findViewById(R.id.ivMeal)

        fun bind(meal: Meal) {
            tvName.text = meal.strMeal
            tvCategory.text = meal.strCategory
            setupHyperlink(tvLink)

            tvLink.setOnClickListener{
                val url = meal.strYoutube
                if(!url.isNullOrEmpty()){
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    ctx.startActivity(i)
                }else Toast.makeText(ctx, "No video available", Toast.LENGTH_SHORT).show()
            }

            ivMeal.toGlide(itemView, meal.strMealThumb, ivMeal)
        }

        private fun setupHyperlink(textView: TextView) {
            textView.apply {
                movementMethod = LinkMovementMethod.getInstance();
                isClickable = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context)
             .inflate(R.layout.item_meal, parent, false)
        return ViewHolder(itemView, ctx!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}