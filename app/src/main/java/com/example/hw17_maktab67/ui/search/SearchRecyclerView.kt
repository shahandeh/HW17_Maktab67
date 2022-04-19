package com.example.hw17_maktab67.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw17_maktab67.databinding.AppRecyclerViewSampleBinding
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import com.example.hw17_maktab67.glide

class SearchRecyclerView(private val itemViewClick: (id: Int) -> Unit) :
    ListAdapter<DiscoverMovieModelResult, SearchRecyclerView.SearchViewHolder>(SearchDiffCallback()) {

    inner class SearchViewHolder(
        private val binding: AppRecyclerViewSampleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"
        fun bind(item: DiscoverMovieModelResult) {
            binding.apply {
                imageViewAppRecyclerViewSample.glide(imageBaseUrl + item.poster_path)
                titleAppRecyclerViewSample.text = item.title
                languageAppRecyclerViewSample.text = item.original_language
                releaseDateAppRecyclerViewSample.text = item.release_date
                voteCountAppRecyclerViewSample.text = item.vote_count.toString()
                ratingBarAppRecyclerViewSample.rating = (item.vote_average / 2).toFloat()
            }
            binding.root.setOnClickListener { itemViewClick(item.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            AppRecyclerViewSampleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}