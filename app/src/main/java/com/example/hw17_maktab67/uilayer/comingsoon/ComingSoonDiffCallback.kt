package com.example.hw17_maktab67.uilayer.comingsoon

import androidx.recyclerview.widget.DiffUtil
import com.example.hw17_maktab67.datalayer.model.commingsoon.ComingSoonModelResult

class ComingSoonDiffCallback: DiffUtil.ItemCallback<ComingSoonModelResult>() {
    override fun areItemsTheSame(oldItem: ComingSoonModelResult, newItem: ComingSoonModelResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ComingSoonModelResult, newItem: ComingSoonModelResult): Boolean {
        return oldItem == newItem
    }
}