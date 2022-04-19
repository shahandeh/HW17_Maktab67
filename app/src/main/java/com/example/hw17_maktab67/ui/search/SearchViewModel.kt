package com.example.hw17_maktab67.ui.search

import android.util.Log
import androidx.lifecycle.*
import com.example.hw17_maktab67.Resource
import com.example.hw17_maktab67.datalayer.Repository
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _serverSearchResult = MutableStateFlow<List<DiscoverMovieModelResult>>(emptyList())
    val serverSearchResult = _serverSearchResult.asStateFlow()

    private var _dataBaseSearchResult = MutableStateFlow<List<DiscoverMovieModelResult>>(emptyList())
    val dataBaseSearchResult = _dataBaseSearchResult.asStateFlow()

    fun searchInServer(input: String) {
        val query = mapOf(
            "api_key" to "6baf19072b7f3cfbe136c6c9d64372ad",
            "query" to input
        )
        viewModelScope.launch {
            repository.searchMovieFromServer(query).collect { result ->
                when (result) {
                    is Resource.Success -> result.data?.let { _serverSearchResult.emit(it.results) }
                    is Resource.Failure -> println(result.error)
                }
            }
        }
    }

    fun searchInDataBase(query: String) {
        viewModelScope.launch {
            repository.searchMovieFromDataBase(query).collect {
                _dataBaseSearchResult.emit(it)
            }
        }
    }
}