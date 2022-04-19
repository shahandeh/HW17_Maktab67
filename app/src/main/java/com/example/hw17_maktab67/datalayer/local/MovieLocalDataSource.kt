package com.example.hw17_maktab67.datalayer.local

import com.example.hw17_maktab67.datalayer.local.db.MovieDao
import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend fun getComingSoon() = movieDao.getComingSoonList()

    suspend fun updateComingSoonList(comingSoonModel: List<ComingSoonModelResult>) {
        movieDao.updateComingSoonList(*comingSoonModel.toTypedArray())
    }

    suspend fun getDiscoverMovieList() = movieDao.getDiscoverMovieList()

    suspend fun updateDiscoverMovieList(discoverMovieModelResult: List<DiscoverMovieModelResult>) {
        movieDao.updateDiscoverMovieList(*discoverMovieModelResult.toTypedArray())
    }

    suspend fun searchMovie(query: String) = movieDao.searchMovie(query)
}