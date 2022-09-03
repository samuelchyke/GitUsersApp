package com.example.gitusers.repositories

import com.example.gitusers.api.GitServiceApi
import com.example.gitusers.model.GitUser
import com.example.gitusers.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

interface GitUsersRepository {
    suspend fun getUsers(): NetworkResult<List<GitUser>>
}

class GitUsersRepositoryImpl @Inject constructor (
    private val gitServiceApi: GitServiceApi
) : GitUsersRepository{

    override suspend fun getUsers(): NetworkResult<List<GitUser>> {
        val response = gitServiceApi.getUsers()
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
}
