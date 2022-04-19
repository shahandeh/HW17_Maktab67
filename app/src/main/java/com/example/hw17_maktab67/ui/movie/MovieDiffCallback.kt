package com.example.hw17_maktab67.ui.movie

import androidx.recyclerview.widget.DiffUtil
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult

class MovieDiffCallback: DiffUtil.ItemCallback<DiscoverMovieModelResult>() {
    override fun areItemsTheSame(
        oldItem: DiscoverMovieModelResult,
        newItem: DiscoverMovieModelResult
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: DiscoverMovieModelResult,
        newItem: DiscoverMovieModelResult
    ) = oldItem == newItem
}