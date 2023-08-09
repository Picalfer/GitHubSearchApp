package com.landfathich.githubsearchapp.data.api

import com.landfathich.githubsearchapp.data.model.DetailUserResponse
import com.landfathich.githubsearchapp.data.model.RepoContent
import com.landfathich.githubsearchapp.data.model.RepoResponse
import com.landfathich.githubsearchapp.data.model.User
import com.landfathich.githubsearchapp.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/users")
    @Headers("Authorization: token ${ApiConstants.token}")
    suspend fun getAllUsersByName(
        @Query("q") query: String,
    ): Response<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ${ApiConstants.token}")
    suspend fun getUserDetail(
        @Path("username") username: String,
    ): Response<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${ApiConstants.token}")
    suspend fun getFollowers(
        @Path("username") username: String,
    ): Response<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${ApiConstants.token}")
    suspend fun getFollowing(
        @Path("username") username: String,
    ): Response<ArrayList<User>>

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
        @Path("path") path: String = "",
    ): Response<RepoContent>
}