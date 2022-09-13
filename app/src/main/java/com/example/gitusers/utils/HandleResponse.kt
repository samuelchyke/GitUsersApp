package com.example.gitusers.utils

import com.example.gitusers.model.GitUser
import com.example.gitusers.model.Search
import retrofit2.Response

interface HandleResponse<T>{
    fun handleResponse(response: Response<T>): NetworkResult<T>
}

class HandleGitUserResponse : HandleResponse<List<GitUser>>{

    override fun handleResponse(response: Response<List<GitUser>>): NetworkResult<List<GitUser>> {
        if (response.isSuccessful) {
            response.body()?.let { responseResult ->
                return NetworkResult.Success(responseResult)
            }
        }
        return NetworkResult.Error(response.message())
    }
}

class HandleSearchResponse : HandleResponse<Search>{

    override fun handleResponse(response: Response<Search>): NetworkResult<Search> {
        if (response.isSuccessful) {
            response.body()?.let { responseResult ->
                return NetworkResult.Success(responseResult)
            }
        }
        return NetworkResult.Error(response.message())
    }
}

