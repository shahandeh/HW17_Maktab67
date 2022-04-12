package com.example.hw17_maktab67.uilayer.search

import android.util.Log
import androidx.lifecycle.*
import com.example.hw17_maktab67.datalayer.Repository
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: Repository
) : ViewModel() {

    private var _serverSearchResult = MutableStateFlow<List<DiscoverMovieModelResult>>(emptyList())
    val serverSearchResult = _serverSearchResult.asStateFlow()

    private var _dataBaseSearchResult = MutableStateFlow<List<DiscoverMovieModelResult>>(emptyList())
    val dataBaseSearchResult = _dataBaseSearchResult.asStateFlow()

    fun searchInServer(input: String) {
        Log.d("majid", "searchMovie: $input")
        val query = mapOf(
            "api_key" to "6baf19072b7f3cfbe136c6c9d64372ad",
            "query" to input
        )
        viewModelScope.launch {

            val result = repository.searchMovieFromServer(query)
            Log.d("majid", "searchMovie: search view model $result")
            result.body()?.let {
                _serverSearchResult.emit(it.results)
            }
        }
    }

    fun searchInDataBase(query: String){
        viewModelScope.launch {
            repository.searchMovieFromDataBase(query).collect{
                _dataBaseSearchResult.emit(it)
            }
        }
    }
}