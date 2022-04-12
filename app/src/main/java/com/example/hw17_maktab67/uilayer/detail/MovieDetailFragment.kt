package com.example.hw17_maktab67.uilayer.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw17_maktab67.R
import com.example.hw17_maktab67.databinding.FragmentMovieDetailBinding
import com.example.hw17_maktab67.di.App
import com.example.hw17_maktab67.glide

class MovieDetailFragment: Fragment(R.layout.fragment_movie_detail) {

    lateinit var poster: ImageView
    lateinit var title: TextView
    lateinit var language: TextView
    lateinit var releaseDate: TextView
    lateinit var overview: TextView
    lateinit var popularity: TextView
    lateinit var voteCount: TextView
    lateinit var voteAverage: RatingBar
    lateinit var binding: FragmentMovieDetailBinding
    private val safeArgs: MovieDetailFragmentArgs by navArgs()
    private val movieDetailFragmentViewModel by viewModels<MovieDetailFragmentViewModel> { MovieDetailViewModelFactory((requireActivity().application as App).serviceLocator.repository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)

        viewInit()

        lifecycleScope.launchWhenStarted { movieDetailFragmentViewModel.detail(safeArgs.id) }

        movieDetailFragmentViewModel.detail.observe(viewLifecycleOwner){
            poster.glide("https://image.tmdb.org/t/p/w500/" + it.poster_path)
            title.text = it.title
            language.text = it.original_language
            releaseDate.text = it.release_date
            overview.text = it.overview
            popularity.text = it.popularity.toString()
            voteCount.text = it.vote_count.toString()
            voteAverage.rating = (it.vote_average/2).toFloat()
        }

        binding.showVideoFragmentMovieDetail.setOnClickListener {
            val action = MovieDetailFragmentDirections.actionMovieDetailFragmentToShowVideoFragment()
            findNavController().navigate(action)
        }

    }

    private fun viewInit(){
        poster = binding.posterMovieDialog
        title = binding.titleMovieDialog
        language = binding.languageMovieDialog
        releaseDate = binding.releaseDateMovieDialog
        overview = binding.overviewMovieDialog
        popularity = binding.popularityMovieDialog
        voteCount = binding.voteCountMovieDialog
        voteAverage = binding.voteAverageMovieDialog
    }

}