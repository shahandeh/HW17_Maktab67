package com.example.hw17_maktab67.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw17_maktab67.R
import com.example.hw17_maktab67.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val movieViewModel by viewModels<MovieViewModel>()
    private var isLoading = false
    private lateinit var binding: FragmentMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        val movieRecyclerView = MovieRecyclerView {
            showDetail(it)
        }

        binding.recyclerViewFragmentMovie.apply {
            adapter = movieRecyclerView
        }

        val layoutManager = LinearLayoutManager(requireContext()).apply {
            binding.recyclerViewFragmentMovie.layoutManager = this
        }

        binding.recyclerViewFragmentMovie.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = movieRecyclerView.itemCount
                val childItemCount = layoutManager.childCount
                val firstValuePosition = layoutManager.findFirstCompletelyVisibleItemPosition()

                if (dy > 0 && firstValuePosition + childItemCount >= totalItemCount && !isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                    movieViewModel.getDiscoverMoveList()
                    isLoading = true
                }
            }
        })

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieViewModel.discoverMovieList.collect {
                    movieRecyclerView.submitList(it)
                    if (it.isNotEmpty()) binding.progressBar.visibility = View.GONE
                    isLoading = false
                }
            }
        }
    }

    private fun showDetail(id: Int) {
        val action = MovieFragmentDirections.actionGlobalMovieDetailFragment(id)
        findNavController().navigate(action)
    }
}