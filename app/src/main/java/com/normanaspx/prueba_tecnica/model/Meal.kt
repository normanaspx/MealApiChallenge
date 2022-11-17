package com.normanaspx.prueba_tecnica.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
Created by Norman on 11/16/2022
 **/
@Entity
data class Meal(
    @PrimaryKey
    @SerializedName("idMeal") val idMeal : Int,
    @SerializedName("strMeal") val strMeal : String,
    @SerializedName("strCategory") val strCategory : String,
    @SerializedName("strYoutube") val strYoutube : String?,
    @SerializedName("strMealThumb") val strMealThumb : String
)
data class MealResponse(
    @SerializedName("meals") val meals : List<Meal>
)
