package com.example.hw17_maktab67.ui.comingsoon

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
import com.example.hw17_maktab67.databinding.FragmentComingSoonBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ComingSoonFragment: Fragment(R.layout.fragment_coming_soon) {

    private val comingSoonViewModel by viewModels<ComingSoonViewModel>()
    private var isLoading = false
    private lateinit var binding: FragmentComingSoonBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentComingSoonBinding.bind(view)

        val comingSoonRecyclerView = ComingSoonRecyclerView{
            showDetail(it)
        }

        binding.recyclerViewFragmentComingSoon.apply {
            adapter = comingSoonRecyclerView
        }

        val layoutManager = LinearLayoutManager(requireContext()).apply {
            binding.recyclerViewFragmentComingSoon.layoutManager = this
        }

        binding.recyclerViewFragmentComingSoon.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = comingSoonRecyclerView.itemCount
                val childItemCount = layoutManager.childCount
                val firstValuePosition = layoutManager.findFirstCompletelyVisibleItemPosition()

                if (dy > 0 && firstValuePosition + childItemCount >= totalItemCount && !isLoading){
                    comingSoonViewModel.getComingSoonList()
                    binding.progressBar.visibility = View.VISIBLE
                    isLoading = true
                }
            }
        })

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                comingSoonViewModel.comingSoonMoveModel.collect{
                    comingSoonRecyclerView.submitList(it)
                    if (it.isNotEmpty()) binding.progressBar.visibility = View.GONE
                    isLoading = false
                }
            }
        }
    }

    private fun showDetail(id: Int) {
        val action = ComingSoonFragmentDirections.actionGlobalMovieDetailFragment(id)
        findNavController().navigate(action)

    }

}