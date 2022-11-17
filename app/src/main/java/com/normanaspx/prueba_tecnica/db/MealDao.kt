package com.normanaspx.prueba_tecnica.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.normanaspx.prueba_tecnica.model.Meal

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(meal: Meal): Long

    @Query("select * from Meal")
    fun getAll(): LiveData<List<Meal>>

    @Query("delete from Meal")
    fun deleteAll()

}