package com.example.hw17_maktab67.datalayer.remote.network

import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModel
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMoviesModel
import com.example.hw17_maktab67.datalayer.model.moviedetails.MovieDetailsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MovieApi {

    @GET("movie/{id}?")
    suspend fun getDetailList(
        @Path("id") id: Int,
        @QueryMap map: Map<String, String>
    ): Response<MovieDetailsModel>

    @GET("movie/upcoming?")
    suspend fun getComingSoon(
        @QueryMap map: Map<String, String>
    ): Response<ComingSoonModel>

    @GET("discover/movie?")
    suspend fun getDiscoverMovieList(
        @QueryMap map: Map<String, String>
    ): Response<DiscoverMoviesModel>

    @GET("search/movie?")
    suspend fun searchMovie(
        @QueryMap map: Map<String, String>
    ): Response<DiscoverMoviesModel>

}