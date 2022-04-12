package com.example.hw17_maktab67.uilayer.comingsoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw17_maktab67.datalayer.Repository

class ComingSoonViewModelFactory(
    private val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ComingSoonViewModel::class.java)) return ComingSoonViewModel(repository) as T
        else throw IllegalArgumentException("View Model Not Found")
    }
}