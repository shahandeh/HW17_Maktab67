package com.example.hw17_maktab67.datalayer.local

import com.example.hw17_maktab67.datalayer.ILocalDataSource
import com.example.hw17_maktab67.datalayer.local.db.MovieDao
import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSource(
    private val movieDao: MovieDao
) : ILocalDataSource {

    override fun getComingSoon(): Flow<List<ComingSoonModelResult>> {
        return movieDao.getComingSoonList()
    }

    override suspend fun updateComingSoonList(comingSoonModel: List<ComingSoonModelResult>) {
        movieDao.updateComingSoonList(*comingSoonModel.toTypedArray())
    }

    override fun getDiscoverMovieList(): Flow<List<DiscoverMovieModelResult>> {
        return movieDao.getDiscoverMovieList()
    }

    override suspend fun updateDiscoverMovieList(discoverMovieModelResult: List<DiscoverMovieModelResult>) {
        movieDao.updateDiscoverMovieList(*discoverMovieModelResult.toTypedArray())
    }

    override fun searchMovie(query: String): Flow<List<DiscoverMovieModelResult>> {
        return movieDao.searchMovie(query)
    }
}