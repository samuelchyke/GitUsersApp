package com.example.gitusers.repositories

import com.example.gitusers.api.GitServiceApi
import com.example.gitusers.model.GitUser
import com.example.gitusers.model.Search
import com.example.gitusers.utils.HandleGitUserResponse
import com.example.gitusers.utils.HandleSearchResponse
import com.example.gitusers.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

interface GitUsersRepository {
    suspend fun getUsers(): Response<List<GitUser>>
    suspend fun searchUsers(letter:String): Response<Search>
}

class GitUsersRepositoryImpl @Inject constructor (
    private val gitServiceApi: GitServiceApi
) : GitUsersRepository{

    override suspend fun getUsers(): Response<List<GitUser>> {
        return gitServiceApi.getUsers()
    }

    override suspend fun searchUsers(letter: String): Response<Search> {
        return gitServiceApi.searchUsers(letter)
    }

}
