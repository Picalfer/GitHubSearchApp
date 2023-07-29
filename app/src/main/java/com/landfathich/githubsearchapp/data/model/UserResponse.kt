package com.landfathich.githubsearchapp.data.model

data class UserResponse(
    val incomplete_results: Boolean,
    val items: List<User>,
    val total_count: Int
)