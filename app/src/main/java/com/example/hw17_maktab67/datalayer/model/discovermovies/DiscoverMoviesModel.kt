package com.example.hw17_maktab67.datalayer.model.discovermovies

import androidx.room.Entity
import androidx.room.PrimaryKey


data class DiscoverMoviesModel(
    val page: Int,
    val results: List<DiscoverMovieModelResult>,
    val total_pages: Int,
    val total_results: Int
)