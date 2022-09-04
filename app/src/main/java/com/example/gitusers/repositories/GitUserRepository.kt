package com.example.gitusers.repositories

import com.example.gitusers.api.GitServiceApi
import com.example.gitusers.model.GitUser
import com.example.gitusers.model.Search
import com.example.gitusers.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

interface GitUsersRepository {
    suspend fun getUsers(): NetworkResult<List<GitUser>>
    suspend fun searchUsers(letter:String): NetworkResult<Search>
}

class GitUsersRepositoryImpl @Inject constructor (
    private val gitServiceApi: GitServiceApi
) : GitUsersRepository{

    override suspend fun getUsers(): NetworkResult<List<GitUser>> {
        val response = gitServiceApi.getUsers()
        return handleGitUserResponse(response)
    }

    override suspend fun searchUsers(letter: String): NetworkResult<Search> {
        val response = gitServiceApi.searchUsers(letter)
        return handleGitUserResponse(response)
    }

    //CONVERT RETROFIT RESPONSE OBJECT TO GENERIC DATA HANDLER (NETWORK RESULT)
    private fun handleGitUserResponse(response: Response<List<GitUser>>): NetworkResult<List<GitUser>> {
        if (response.isSuccessful) {
            response.body()?.let { responseResult ->
                return NetworkResult.Success(responseResult)
            }
        }
        return NetworkResult.Error(response.message())
    }

    @JvmName("handleGitUserResponse1")
    private fun handleGitUserResponse(response: Response<Search>): NetworkResult<Search> {
        if (response.isSuccessful) {
            response.body()?.let { responseResult ->
                return NetworkResult.Success(responseResult)
            }
        }
        return NetworkResult.Error(response.message())
    }
}
