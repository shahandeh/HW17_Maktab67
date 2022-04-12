package com.example.hw17_maktab67.uilayer.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.hw17_maktab67.R
import com.example.hw17_maktab67.databinding.FragmentSearchBinding
import com.example.hw17_maktab67.di.App
import kotlinx.coroutines.launch

class SearchFragment: Fragment(R.layout.fragment_search) {

    private val searchViewModel by viewModels<SearchViewModel>{
        SearchViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchBinding.bind(view)
        val recyclerViewAdapter = SearchRecyclerView()

        binding.recyclerViewFragmentSearch.apply {
            adapter = recyclerViewAdapter
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){

                launch {
                    searchViewModel.serverSearchResult.collect{
                        recyclerViewAdapter.submitList(it)
                    }
                }

                launch {
                    searchViewModel.dataBaseSearchResult.collect{
                        recyclerViewAdapter.submitList(it)
                    }
                }
            }
        }

        binding.searchViewFragmentSearch.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.searchInServer(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchViewModel.searchInDataBase(newText.toString())
                return false
            }
        })

    }

}