package com.example.hw17_maktab67.uilayer.comingsoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw17_maktab67.datalayer.Repository
import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ComingSoonViewModel(
    private val repository: Repository
): ViewModel() {

    private val query = mapOf(
        "api_key" to "6baf19072b7f3cfbe136c6c9d64372ad",
        "language" to "en-US",
        "page" to "1"
    )

    private val _comingSoonMovieList: MutableStateFlow<List<ComingSoonModelResult>> = MutableStateFlow(emptyList())
    val comingSoonMoveModel: StateFlow<List<ComingSoonModelResult>> = _comingSoonMovieList

    init {
        viewModelScope.launch {
            repository.comingSoonList(query).collect{
                _comingSoonMovieList.emit(it)
            }
        }
    }

}