package com.example.hw17_maktab67.ui.comingsoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw17_maktab67.Resource
import com.example.hw17_maktab67.datalayer.Repository
import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComingSoonViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var pageNumber = 0
    private var query = mutableMapOf(
        "api_key" to "6baf19072b7f3cfbe136c6c9d64372ad",
        "language" to "en-US",
        "page" to "1"
    )

    private val _comingSoonMovieList: MutableStateFlow<List<ComingSoonModelResult>> = MutableStateFlow(emptyList())
    val comingSoonMoveModel: StateFlow<List<ComingSoonModelResult>> = _comingSoonMovieList

    init {
        getComingSoonList()
    }

    fun getComingSoonList(){
        pageNumber += 1
        query["page"] = pageNumber.toString()

        viewModelScope.launch {
            repository.comingSoonList(query).collect{ resource ->
                when (resource){
                    is Resource.Success -> resource.data?.let { _comingSoonMovieList.emit(it) }
                    is Resource.Failure -> {
                        pageNumber -= 1
                        print(resource.error)
                    }
                }
            }
        }
    }

}