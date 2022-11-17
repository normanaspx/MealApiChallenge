package com.normanaspx.prueba_tecnica.network

import com.normanaspx.prueba_tecnica.model.Meal
import com.normanaspx.prueba_tecnica.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET

interface MealService {
    @GET("random.php")
    suspend fun getMeals(): MealResponse
}