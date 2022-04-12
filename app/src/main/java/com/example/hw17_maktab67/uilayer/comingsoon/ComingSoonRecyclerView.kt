package com.example.hw17_maktab67.uilayer.comingsoon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw17_maktab67.databinding.AppRecyclerViewSampleBinding
import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult
import com.example.hw17_maktab67.glide

class ComingSoonRecyclerView: ListAdapter<ComingSoonModelResult, ComingSoonRecyclerView.ComingSoonViewHolder>(ComingSoonDiffCallback()) {


    class ComingSoonViewHolder(
        private val binding: AppRecyclerViewSampleBinding
    ):RecyclerView.ViewHolder(binding.root){
        private val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"
        fun bind(movie: ComingSoonModelResult){
            binding.apply {
                imageViewAppRecyclerViewSample.glide(imageBaseUrl + movie.poster_path)
                titleAppRecyclerViewSample.text = movie.title
                languageAppRecyclerViewSample.text = movie.original_language
                releaseDateAppRecyclerViewSample.text = movie.release_date
                voteCountAppRecyclerViewSample.text = movie.vote_count.toString()
                ratingBarAppRecyclerViewSample.rating = movie.vote_average.div(2).toFloat()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingSoonViewHolder {
        return ComingSoonViewHolder(AppRecyclerViewSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ComingSoonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}