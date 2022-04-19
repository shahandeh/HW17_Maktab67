package com.example.hw17_maktab67.datalayer.model.discovermovies

import androidx.room.Entity
import androidx.room.PrimaryKey

data class DiscoverMoviesModel(
    val results: List<DiscoverMovieModelResult>
)

@Entity(tableName = "discover_movie")
data class DiscoverMovieModelResult(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)