package com.example.hw17_maktab67.datalayer.local.db

import androidx.room.*
import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import com.example.hw17_maktab67.datalayer.model.moviedetails.MovieDetailsModel

@Database(
    entities = [ComingSoonModelResult::class, DiscoverMovieModelResult::class, MovieDetailsModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao


}