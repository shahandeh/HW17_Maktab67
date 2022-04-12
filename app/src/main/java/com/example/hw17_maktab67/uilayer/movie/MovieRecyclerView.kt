package com.example.hw17_maktab67.uilayer.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw17_maktab67.databinding.AppRecyclerViewSampleBinding
import com.example.hw17_maktab67.datalayer.model.discovermovies.DiscoverMovieModelResult
import com.example.hw17_maktab67.glide

class MovieRecyclerView(
    private val movieList: List<DiscoverMovieModelResult>,
    private val itemViewClick: ItemViewClick
) : RecyclerView.Adapter<MovieRecyclerView.RecyclerViewHolder>() {

    inner class RecyclerViewHolder(private val binding: AppRecyclerViewSampleBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            itemViewClick.itemClick(movieList[bindingAdapterPosition].id)
        }

        fun bind(item: DiscoverMovieModelResult) {
            val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"
            binding.apply {
                imageViewAppRecyclerViewSample.glide(imageBaseUrl + item.poster_path)
                titleAppRecyclerViewSample.text = item.title
                languageAppRecyclerViewSample.text = item.original_language
                releaseDateAppRecyclerViewSample.text = item.release_date
                voteCountAppRecyclerViewSample.text = item.id.toString()
                ratingBarAppRecyclerViewSample.rating = (item.vote_average / 2).toFloat()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder =
        RecyclerViewHolder(
            AppRecyclerViewSampleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    interface ItemViewClick {
        fun itemClick(id: Int)
    }

}