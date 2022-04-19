package com.example.hw17_maktab67.ui.detail

import androidx.lifecycle.ViewModel
import com.example.hw17_maktab67.datalayer.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailFragmentViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val query = mapOf(
        "api_key" to "6baf19072b7f3cfbe136c6c9d64372ad",
        "append_to_response" to "videos"
    )

    suspend fun detail(id: Int) = repository.detailList(id, query)

}