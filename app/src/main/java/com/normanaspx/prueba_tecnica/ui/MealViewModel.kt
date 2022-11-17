package com.normanaspx.prueba_tecnica.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.normanaspx.prueba_tecnica.model.Meal
import com.normanaspx.prueba_tecnica.model.MealResponse
import com.normanaspx.prueba_tecnica.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
Created by Norman on 11/16/2022
 **/
private const val MAX_ITERS = 20
@HiltViewModel
class MealViewModel @Inject constructor(private val mealRepository: MealRepository) : ViewModel() {

    val meals : LiveData<List<Meal>> get() = mealRepository.meals
    private var itersCount = 0

    init{
        viewModelScope.launch {
            mealRepository.clearDatabase()
            while(itersCount < MAX_ITERS ){
                withContext(Dispatchers.Default) {
                    mealRepository.getMealData()
                    itersCount = mealRepository.listOfIds.size
                }
            }
        }
    }
}