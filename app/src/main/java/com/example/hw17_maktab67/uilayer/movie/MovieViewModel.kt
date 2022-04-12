package com.example.hw17_maktab67.uilayer.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw17_maktab67.datalayer.Repository
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(
    val repository: Repository
) : ViewModel() {

    var pageNumber = 0
    private var query = mutableMapOf(
        "api_key" to "6baf19072b7f3cfbe136c6c9d64372ad",
        "language" to "en-US",
        "page" to pageNumber.toString()
    )

    private var _discoverMovieList = MutableStateFlow<List<DiscoverMovieModelResult>>(emptyList())
    val discoverMovieList = _discoverMovieList.asStateFlow()

    fun getDiscoverMoveList(input: Int){
        pageNumber += input
        query["page"] = pageNumber.toString()

        viewModelScope.launch {
            repository.discoverMovieList(query).collect{
                _discoverMovieList.emit(it)
                pageNumber = it.size / 20
            }
        }
    }

}