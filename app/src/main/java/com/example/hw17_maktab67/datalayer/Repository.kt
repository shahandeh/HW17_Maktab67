package com.example.hw17_maktab67.datalayer

import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMoviesModel
import com.example.hw17_maktab67.datalayer.model.moviedetails.MovieDetailsModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class Repository(
    private val movieLocalDataSource: ILocalDataSource,
    private val movieRemoteDataSource: IRemoteDataSource
) {

    suspend fun comingSoonList(map: Map<String, String>): Flow<List<ComingSoonModelResult>> {
        val response = movieRemoteDataSource.getComingSoon(map)
        if (response.isSuccessful) response.body()?.let {
            movieLocalDataSource.updateComingSoonList(it.results)
        }
        return movieLocalDataSource.getComingSoon()
    }

    suspend fun detailList(id: Int, map: Map<String, String>): Response<MovieDetailsModel> {
        return movieRemoteDataSource.getDetailList(id, map)
    }

    suspend fun discoverMovieList(map: Map<String, String>): Flow<List<DiscoverMovieModelResult>> {
        val response = movieRemoteDataSource.getDiscoverMovieList(map)
        if (response.isSuccessful) response.body()?.let{
            movieLocalDataSource.updateDiscoverMovieList(it.results)
        }
        return movieLocalDataSource.getDiscoverMovieList()
    }

    suspend fun searchMovieFromServer(map: Map<String, String>): Response<DiscoverMoviesModel> {
        return movieRemoteDataSource.searchMovie(map)
    }

    fun searchMovieFromDataBase(query: String): Flow<List<DiscoverMovieModelResult>>{
        return movieLocalDataSource.searchMovie(query)
    }

}