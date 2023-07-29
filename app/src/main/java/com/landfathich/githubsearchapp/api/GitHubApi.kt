package com.landfathich.githubsearchapp.api

import com.landfathich.githubsearchapp.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getAllUsersByName(
        @Query("q") query: String
    ): Call<UserResponse>
}