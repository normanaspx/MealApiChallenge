package com.normanaspx.prueba_tecnica.repository

import androidx.lifecycle.LiveData
import com.normanaspx.prueba_tecnica.db.MealDao
import com.normanaspx.prueba_tecnica.model.Meal
import com.normanaspx.prueba_tecnica.model.MealResponse
import com.normanaspx.prueba_tecnica.network.MealService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
Created by Norman on 11/16/2022
 **/

/**
 * Class to handle API calls to get the Meals
 *
 * @author Norman Vicente
 *
 * */
class MealRepository @Inject constructor(private val mealService: MealService,
                                         private val mealDao: MealDao) {

    val listOfIds: MutableList<Int> = mutableListOf()
    val meals : LiveData<List<Meal>> get() = mealDao.getAll()

    /**
     * This method gets the meals from the service
     * */
    suspend fun getMealData(){
        val meal = mapData(mealService.getMeals())
        if(!existInList(meal.idMeal)){
            insertMeal(meal)
        }
    }

    suspend fun clearDatabase() = withContext(Dispatchers.IO) {
        mealDao.deleteAll()
    }

    private suspend fun insertMeal(meal: Meal){
        mealDao.insert(meal)
    }

    private fun mapData(result: MealResponse): Meal{
        lateinit var meal: Meal
        result.meals.map {
            meal = it
        }
        return meal
    }

    private fun existInList(id: Int): Boolean {
        return if(listOfIds.contains(id)){
            true
        }else{
            listOfIds.add(id)
            false
        }
    }
}