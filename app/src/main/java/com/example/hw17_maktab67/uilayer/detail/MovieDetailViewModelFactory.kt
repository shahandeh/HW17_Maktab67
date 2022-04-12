package com.example.hw17_maktab67.uilayer.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw17_maktab67.datalayer.Repository

class MovieDetailViewModelFactory(
    private val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieDetailFragmentViewModel::class.java)) MovieDetailFragmentViewModel(repository) as T
        else throw IllegalArgumentException("View Model Not Found")
    }
}