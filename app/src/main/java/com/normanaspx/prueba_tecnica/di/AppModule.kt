package com.normanaspx.prueba_tecnica.di

import android.content.Context
import androidx.room.Room
import com.normanaspx.prueba_tecnica.MainApplication
import com.normanaspx.prueba_tecnica.db.Database
import com.normanaspx.prueba_tecnica.network.MealService
import com.normanaspx.prueba_tecnica.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


/**
Created by Norman on 11/16/2022
 **/

/**
 * This component is used for general purposes
 * it's placed in the application component since
 * it can be injected on any other modules where
 * it is needed
 *
 * @author Norman Vicente
 *
 * */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesMealService(retrofit: Retrofit): MealService = retrofit.create(MealService::class.java)


    @Provides
    fun provideDao(db: Database) = db.mealDao()

    @Singleton
    @Provides
    fun provideRoomInstace(@ApplicationContext ctx: Context)
    = Room.databaseBuilder(ctx, Database::class.java,Constants.DB_NAME).build()

}