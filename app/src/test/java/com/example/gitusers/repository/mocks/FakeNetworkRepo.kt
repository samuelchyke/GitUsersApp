package com.example.gitusers.repository.mocks

import com.example.gitusers.model.GitUser
import com.example.gitusers.model.Search
import com.example.gitusers.repositories.GitUsersRepository
import com.example.gitusers.utils.NetworkResult
import retrofit2.Response

class FakeNetworkRepo: GitUsersRepository {

    private var networkError = false

    fun returnNetworkError(value: Boolean){
        networkError = value
    }

    override suspend fun getUsers(): Response<List<GitUser>> {
        return Response.success(
            listOf(
                GitUser(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    0,
                    "",
                    "",
                    "",
                    "",
                    "",
                    true,
                    "",
                    "",
                    "",
                    "",

        )))
    }

    override suspend fun searchUsers(letter: String): Response<Search> {
        return Response.success(Search(
            incomplete_results = false,
            items = (listOf(
                GitUser(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    0,
                    "",
                    "",
                    "",
                    "",
                    "",
                    true,
                    "",
                    "",
                    "",
                    "",
                )
        )),
            total_count = 0))

    }


}