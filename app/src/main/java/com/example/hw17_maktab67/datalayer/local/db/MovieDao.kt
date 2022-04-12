package com.example.hw17_maktab67.datalayer.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateComingSoonList(vararg comingSoonModel: ComingSoonModelResult)

    @Query("SELECT * FROM coming_soon")
    fun getComingSoonList(): Flow<List<ComingSoonModelResult>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateDiscoverMovieList(vararg discoverMovieModelResult: DiscoverMovieModelResult)

    @Query("SELECT * FROM discover_movie")
    fun getDiscoverMovieList(): Flow<List<DiscoverMovieModelResult>>

    @Query("SELECT * FROM discover_movie WHERE title LIKE '%' || :query || '%' ")
    fun searchMovie(query: String): Flow<List<DiscoverMovieModelResult>>
}