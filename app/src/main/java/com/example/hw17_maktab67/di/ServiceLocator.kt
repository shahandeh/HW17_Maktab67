package com.example.hw17_maktab67.di

import android.app.Application
import com.example.hw17_maktab67.datalayer.Repository
import com.example.hw17_maktab67.datalayer.local.MovieLocalDataSource
import com.example.hw17_maktab67.datalayer.local.db.AppDataBase
import com.example.hw17_maktab67.datalayer.remote.MovieRemoteDataSource
import com.example.hw17_maktab67.datalayer.remote.network.NetworkManager

class ServiceLocator(application: Application) {

    private val localDataSource = MovieLocalDataSource(AppDataBase.getDatabase(application).movieDao())
    private val remoteDataSource = MovieRemoteDataSource(NetworkManager.service)
    val repository: Repository = Repository(localDataSource, remoteDataSource)

}