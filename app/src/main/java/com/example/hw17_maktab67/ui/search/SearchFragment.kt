package com.example.hw17_maktab67.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.hw17_maktab67.R
import com.example.hw17_maktab67.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val searchViewModel by viewModels<SearchViewModel>()

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchRecyclerView: SearchRecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)
        searchRecyclerView = SearchRecyclerView{
            showDetail(it)
        }

        binding.recyclerViewFragmentSearch.apply {
            adapter = searchRecyclerView
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    searchViewModel.serverSearchResult.collect {
                        searchRecyclerView.submitList(it)
                        if (it.isNotEmpty()) binding.progressBar.visibility = View.GONE
                    }
                }

                launch {
                    searchViewModel.dataBaseSearchResult.collect {
                        searchRecyclerView.submitList(it)
                        if (it.isNotEmpty()) binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }

        binding.searchViewFragmentSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.searchInServer(query.toString())
                binding.progressBar.visibility = View.VISIBLE
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchViewModel.searchInDataBase(newText.toString())
                binding.progressBar.visibility = View.VISIBLE
                return true
            }
        })
    }

    private fun showDetail(id: Int) {
        val action = SearchFragmentDirections.actionGlobalMovieDetailFragment(id)
        findNavController().navigate(action)
    }
}