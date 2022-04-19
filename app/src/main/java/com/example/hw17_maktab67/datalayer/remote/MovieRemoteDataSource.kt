package com.example.hw17_maktab67.datalayer.remote

import com.example.hw17_maktab67.datalayer.remote.network.MovieApi
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
) {

    suspend fun getComingSoon(map: Map<String, String>) = movieApi.getComingSoon(map)

    suspend fun getDetailList(id: Int, map: Map<String, String>) = movieApi.getDetailList(id, map)

    suspend fun getDiscoverMovieList(map: Map<String, String>) = movieApi.getDiscoverMovieList(map)

    suspend fun searchMovie(map: Map<String, String>) = movieApi.searchMovie(map)

}