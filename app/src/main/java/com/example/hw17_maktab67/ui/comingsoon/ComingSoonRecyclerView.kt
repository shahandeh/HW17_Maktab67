package com.example.hw17_maktab67.ui.comingsoon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw17_maktab67.databinding.AppRecyclerViewSampleBinding
import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult
import com.example.hw17_maktab67.glide

class ComingSoonRecyclerView(
    private val itemViewClick: (id: Int) -> Unit
) : ListAdapter<ComingSoonModelResult, ComingSoonRecyclerView.ComingSoonViewHolder>(
    ComingSoonDiffCallback()
) {

    inner class ComingSoonViewHolder(private val binding: AppRecyclerViewSampleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"
        fun bind(item: ComingSoonModelResult) {
            binding.apply {
                imageViewAppRecyclerViewSample.glide(imageBaseUrl + item.poster_path)
                titleAppRecyclerViewSample.text = item.title
                languageAppRecyclerViewSample.text = item.original_language
                releaseDateAppRecyclerViewSample.text = item.release_date
                voteCountAppRecyclerViewSample.text = item.vote_count.toString()
                ratingBarAppRecyclerViewSample.rating = item.vote_average.div(2).toFloat()
            }
            binding.root.setOnClickListener { itemViewClick(item.id) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ComingSoonViewHolder(
            AppRecyclerViewSampleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ComingSoonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}