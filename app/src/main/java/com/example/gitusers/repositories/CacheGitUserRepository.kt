package com.example.gitusers.repositories

import androidx.lifecycle.LiveData
import com.example.gitusers.cache.GitUserDAO
import com.example.gitusers.model.CacheGitUser
import javax.inject.Inject

interface CacheGitUserRepository {

    suspend fun insertGitUserToDatabase(gitUser: List<CacheGitUser>?)

    suspend fun getGitUserFromDatabase() : LiveData<List<CacheGitUser>>

}

class CacheGitUserRepositoryImpl @Inject constructor(
    private val gitUserDAO: GitUserDAO
): CacheGitUserRepository{

    override suspend fun insertGitUserToDatabase(gitUser: List<CacheGitUser>?) {
       return gitUserDAO.insertGitUsers(gitUser)
    }

    override suspend fun getGitUserFromDatabase(): LiveData<List<CacheGitUser>> {
        return gitUserDAO.getGitUsers()
    }

}