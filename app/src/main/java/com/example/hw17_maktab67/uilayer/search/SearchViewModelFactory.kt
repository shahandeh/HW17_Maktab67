package com.example.hw17_maktab67.uilayer.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw17_maktab67.datalayer.Repository

class SearchViewModelFactory(
    private val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) return SearchViewModel(repository) as T
        else throw (IllegalArgumentException("View Model Not Found"))
    }

}