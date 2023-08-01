package com.landfathich.githubsearchapp.data.api

import com.landfathich.githubsearchapp.data.model.UserResponse
import com.landfathich.githubsearchapp.data.model.RepoResponse
import com.landfathich.githubsearchapp.data.model.RepoContent
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Path

interface GitHubApi {
    @GET("search/users")
    @Headers("Authorization: token ${ApiConstants.token}")
    suspend fun getAllUsersByName(
        @Query("q") query: String,
    ): Response<UserResponse>

    @GET("search/repositories")
    @Headers("Authorization: token ${ApiConstants.token}")
    suspend fun getAllReposByName(
        @Query("q") query: String,
    ): Response<RepoResponse>

    @GET("/repos/{owner}/{repo}/contents/{path}")
    @Headers("Authorization: token ${ApiConstants.token}")
    suspend fun getRepoContent(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("path") path: String = ""
    ): Response<RepoContent>
}