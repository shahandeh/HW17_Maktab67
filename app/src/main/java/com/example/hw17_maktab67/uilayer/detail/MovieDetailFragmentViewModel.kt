package com.example.hw17_maktab67.uilayer.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw17_maktab67.datalayer.Repository
import com.example.hw17_maktab67.datalayer.model.moviedetails.MovieDetailsModel

class MovieDetailFragmentViewModel(
    private val repository: Repository
): ViewModel() {

    private val query = mapOf(
        "api_key" to "6baf19072b7f3cfbe136c6c9d64372ad",
        "append_to_response" to "videos"
    )

    private val _detail = MutableLiveData<MovieDetailsModel>()
    val detail: LiveData<MovieDetailsModel> = _detail

    suspend fun detail(id: Int) {
        val response = repository.detailList(id, query)
        if (response.isSuccessful) response.body()?.let {
            _detail.postValue(it)
        }
    }

}