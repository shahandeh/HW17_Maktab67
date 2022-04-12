package com.example.hw17_maktab67.datalayer.remote

import com.example.hw17_maktab67.datalayer.IRemoteDataSource
import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModel
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMoviesModel
import com.example.hw17_maktab67.datalayer.model.moviedetails.MovieDetailsModel

import com.example.hw17_maktab67.datalayer.remote.network.MovieApi
import retrofit2.Response

class MovieRemoteDataSource(
    private val movieApi: MovieApi
): IRemoteDataSource {

    override suspend fun getComingSoon(map: Map<String, String>): Response<ComingSoonModel> {
        return movieApi.getComingSoon(map)
    }

    override suspend fun getDetailList(id: Int, map: Map<String, String>): Response<MovieDetailsModel> {
        return movieApi.getDetailList(id, map)
    }

    override suspend fun getDiscoverMovieList(map: Map<String, String>): Response<DiscoverMoviesModel> {
        return movieApi.getDiscoverMovieList(map)
    }

    override suspend fun searchMovie(map: Map<String, String>): Response<DiscoverMoviesModel> {
        return movieApi.searchMovie(map)
    }

}