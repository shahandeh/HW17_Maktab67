package com.example.hw17_maktab67.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw17_maktab67.Resource
import com.example.hw17_maktab67.datalayer.Repository
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private var pageNumber = 0
    private var query = mutableMapOf(
        "api_key" to "6baf19072b7f3cfbe136c6c9d64372ad",
        "language" to "en-US",
        "page" to pageNumber.toString()
    )

    private var _discoverMovieList = MutableStateFlow<List<DiscoverMovieModelResult>>(emptyList())
    val discoverMovieList = _discoverMovieList.asStateFlow()

    init {
        getDiscoverMoveList()
    }

    fun getDiscoverMoveList() {
        pageNumber += 1
        query["page"] = pageNumber.toString()

        viewModelScope.launch {
            repository.discoverMovieList(query).collect { result ->
                when (result) {
                    is Resource.Success -> result.data?.let { _discoverMovieList.emit(it) }
                    is Resource.Failure -> {
                        pageNumber -= 1
                        print(result)
                    }
                }
            }
        }
    }

}