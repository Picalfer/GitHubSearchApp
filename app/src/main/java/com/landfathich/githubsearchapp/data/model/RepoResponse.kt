package com.landfathich.githubsearchapp.data.model

data class RepoResponse(
    val incomplete_results: Boolean,
    val items: ArrayList<Repo>,
    val total_count: Int
)