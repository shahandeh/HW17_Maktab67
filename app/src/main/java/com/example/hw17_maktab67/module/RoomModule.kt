package com.example.hw17_maktab67.module

import android.content.Context
import androidx.room.Room
import com.example.hw17_maktab67.datalayer.local.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun dataBase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, "movie_database").build()

    @Provides
    @Singleton
    fun dao(appDataBase: AppDataBase) = appDataBase.movieDao()

}