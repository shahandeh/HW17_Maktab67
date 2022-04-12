package com.example.hw17_maktab67.uilayer.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw17_maktab67.databinding.AppRecyclerViewSampleBinding
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import com.example.hw17_maktab67.glide

class SearchRecyclerView: ListAdapter<DiscoverMovieModelResult, SearchRecyclerView.SearchViewHolder>(SearchDiffCallback()) {

    class SearchViewHolder(
        private val binding: AppRecyclerViewSampleBinding
    ): RecyclerView.ViewHolder(binding.root){
        private val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"
        fun bind(movie: DiscoverMovieModelResult){
            binding.apply {
                imageViewAppRecyclerViewSample.glide(imageBaseUrl + movie.poster_path)
                titleAppRecyclerViewSample.text = movie.title
                languageAppRecyclerViewSample.text = movie.original_language
                releaseDateAppRecyclerViewSample.text = movie.release_date
                voteCountAppRecyclerViewSample.text = movie.vote_count.toString()
                ratingBarAppRecyclerViewSample.rating = (movie.vote_average/2).toFloat()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(AppRecyclerViewSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}