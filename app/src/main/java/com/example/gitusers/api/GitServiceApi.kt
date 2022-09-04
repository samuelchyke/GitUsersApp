package com.example.gitusers.api

import com.example.gitusers.model.GitUser
import com.example.gitusers.model.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitServiceApi {

    @GET(USER_PATH)
    suspend fun getUsers(): Response<List<GitUser>>

    @GET(SEARCH_PATH)
    suspend fun searchUsers(
        @Query("q") letter: String,
        @Query("per_page") results: Int = 5
    ): Response<Search>

    companion object{

        const val BASE_URL = "https://api.github.com/"
        private const val USER_PATH = "users"
        private const val SEARCH_PATH = "search/users"




    }
}