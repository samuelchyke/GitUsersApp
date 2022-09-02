package com.example.gitusers.api

import com.example.gitusers.model.GitUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitServiceApi {

    @GET(USER_PATH)
    suspend fun getUsers(): Response<List<GitUser>>

    companion object{

        const val BASE_URL = "https://api.github.com/"
        private const val USER_PATH = "users"

    }
}