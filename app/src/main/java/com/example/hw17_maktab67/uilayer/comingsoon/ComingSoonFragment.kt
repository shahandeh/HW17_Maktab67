package com.example.hw17_maktab67.uilayer.comingsoon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.hw17_maktab67.R
import com.example.hw17_maktab67.databinding.FragmentComingSoonBinding
import com.example.hw17_maktab67.di.App

class ComingSoonFragment: Fragment(R.layout.fragment_coming_soon) {

    private val comingSoonViewModel by viewModels<ComingSoonViewModel>{
        ComingSoonViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    }

    lateinit var binding: FragmentComingSoonBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentComingSoonBinding.bind(view)

        val recyclerView = ComingSoonRecyclerView()

        binding.recyclerViewFragmentComingSoon.apply {
            adapter = recyclerView
        }

        lifecycleScope.launchWhenStarted {
            comingSoonViewModel.comingSoonMoveModel.collect{
                recyclerView.submitList(it)
            }
        }
    }

}