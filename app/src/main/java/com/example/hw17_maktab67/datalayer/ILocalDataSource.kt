package com.example.hw17_maktab67.datalayer


import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {

    fun getComingSoon(): Flow<List<ComingSoonModelResult>>

    suspend fun updateComingSoonList(comingSoonModel: List<ComingSoonModelResult>)

    fun getDiscoverMovieList(): Flow<List<DiscoverMovieModelResult>>

    suspend fun updateDiscoverMovieList(discoverMovieModelResult: List<DiscoverMovieModelResult>)

    fun searchMovie(query: String): Flow<List<DiscoverMovieModelResult>>

}