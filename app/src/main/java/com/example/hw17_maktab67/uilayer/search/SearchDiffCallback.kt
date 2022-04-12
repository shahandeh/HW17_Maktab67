package com.example.hw17_maktab67.uilayer.search

import androidx.recyclerview.widget.DiffUtil
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult

class SearchDiffCallback: DiffUtil.ItemCallback<DiscoverMovieModelResult>() {
    override fun areItemsTheSame(oldItem: DiscoverMovieModelResult, newItem: DiscoverMovieModelResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DiscoverMovieModelResult, newItem: DiscoverMovieModelResult): Boolean {
        return oldItem == newItem
    }
}