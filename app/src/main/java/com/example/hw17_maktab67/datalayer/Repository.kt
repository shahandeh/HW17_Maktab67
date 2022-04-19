package com.example.hw17_maktab67.datalayer

import com.example.hw17_maktab67.Resource
import com.example.hw17_maktab67.datalayer.local.MovieLocalDataSource
import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMoviesModel
import com.example.hw17_maktab67.datalayer.model.moviedetails.MovieDetailsModel
import com.example.hw17_maktab67.datalayer.remote.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) {

    suspend fun comingSoonList(map: Map<String, String>): Flow<Resource<List<ComingSoonModelResult>>> {

        return try {
            val response = movieRemoteDataSource.getComingSoon(map)
            movieLocalDataSource.updateComingSoonList(response.results)
            flow { emit(Resource.Success(response.results)) }
        } catch (e: IOException){
            flow { emit(Resource.Failure(null, e.message)) }
            flow { emit(Resource.Success(movieLocalDataSource.getComingSoon())) }
        }
    }

    suspend fun detailList(id: Int, map: Map<String, String>): Flow<Resource<MovieDetailsModel>>{

        return try {
            val response = movieRemoteDataSource.getDetailList(id, map)
            flow { emit(Resource.Success(response)) }
        } catch (e: IOException){
            flow { emit(Resource.Failure(null, e.message)) }
        }
    }

    suspend fun discoverMovieList(map: Map<String, String>): Flow<Resource<List<DiscoverMovieModelResult>>> {

        return try {
            val response = movieRemoteDataSource.getDiscoverMovieList(map)
            movieLocalDataSource.updateDiscoverMovieList(response.results)
            flow { emit(Resource.Success(response.results)) }
        } catch (e: IOException){
            flow { emit(Resource.Failure(null, e.message)) }
            flow { emit(Resource.Success(movieLocalDataSource.getDiscoverMovieList())) }
        }
    }

    suspend fun searchMovieFromServer(map: Map<String, String>): Flow<Resource<DiscoverMoviesModel>> {

        return try {
            val response = movieRemoteDataSource.searchMovie(map)
            flow { emit(Resource.Success(response)) }
        } catch (e: IOException){
            flow { emit(Resource.Failure(null, e.message)) }
        }
    }

    suspend fun searchMovieFromDataBase(query: String) =
        movieLocalDataSource.searchMovie(query)

}