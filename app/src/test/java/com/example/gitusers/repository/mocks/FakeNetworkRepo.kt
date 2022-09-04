package com.example.gitusers.repository.mocks

import com.example.gitusers.model.GitUser
import com.example.gitusers.model.Search
import com.example.gitusers.repositories.GitUsersRepository
import com.example.gitusers.utils.NetworkResult

class FakeNetworkRepo: GitUsersRepository {

    private var networkError = false

    fun returnNetworkError(value: Boolean){
        networkError = value
    }

    override suspend fun getUsers(): NetworkResult<List<GitUser>> {
        return NetworkResult.Success((listOf(
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
                ))))

    }

    override suspend fun searchUsers(letter: String): NetworkResult<Search> {
        return NetworkResult.Success(Search(
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