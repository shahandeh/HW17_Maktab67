package com.example.hw17_maktab67.uilayer.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw17_maktab67.R
import com.example.hw17_maktab67.di.App
import com.example.hw17_maktab67.uilayer.detail.MovieDetailFragmentDirections
import kotlinx.coroutines.launch

class MovieFragment : Fragment(R.layout.fragment_movie), MovieRecyclerView.ItemViewClick {

    private val movieViewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_fragment_movie)
        recyclerView.adapter = MovieRecyclerView(emptyList(), this)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        var loading = true
        var pastVisibleItems = 0
        var visibleItemCount: Int
        var totalItemCount: Int
        movieViewModel.pageNumber = layoutManager.childCount / 2
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            movieViewModel.getDiscoverMoveList(1)
                            loading = false
                        }
                    }
                }
            }
        })

        movieViewModel.getDiscoverMoveList(1)
        lifecycleScope.launch {
            movieViewModel.discoverMovieList.collect {
                loading = true
                recyclerView.adapter = MovieRecyclerView(it, this@MovieFragment)
                recyclerView.adapter?.notifyItemInserted(layoutManager.childCount + 1)
                recyclerView.scrollToPosition(pastVisibleItems)
            }
        }

    }

    override fun itemClick(id: Int) {
        val action = MovieDetailFragmentDirections.actionGlobalMovieDetailFragment(id)
        findNavController().navigate(action)
    }
}