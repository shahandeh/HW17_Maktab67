package com.example.hw17_maktab67.datalayer.model.commingsoon


data class ComingSoonModel(
    val page: Int,
    val dates: Dates,
    val results: List<ComingSoonModelResult>,
    val total_pages: Int,
    val total_results: Int
)