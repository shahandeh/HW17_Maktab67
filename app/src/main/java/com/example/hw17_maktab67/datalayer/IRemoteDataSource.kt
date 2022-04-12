package com.example.hw17_maktab67.datalayer


import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModel
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMoviesModel
import com.example.hw17_maktab67.datalayer.model.moviedetails.MovieDetailsModel
import retrofit2.Response

interface IRemoteDataSource {

    suspend fun getComingSoon(map: Map<String, String>): Response<ComingSoonModel>

    suspend fun getDetailList(id: Int, map: Map<String, String>): Response<MovieDetailsModel>

    suspend fun getDiscoverMovieList(map: Map<String, String>): Response<DiscoverMoviesModel>

    suspend fun searchMovie(map: Map<String, String>): Response<DiscoverMoviesModel>
}