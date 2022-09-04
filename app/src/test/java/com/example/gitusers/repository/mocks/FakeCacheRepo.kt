package com.example.gitusers.repository.mocks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitusers.model.CacheGitUser
import com.example.gitusers.repositories.CacheGitUserRepository

class FakeCacheRepo: CacheGitUserRepository {

    private val _users = mutableListOf<CacheGitUser>()
    private val obsUsers= MutableLiveData<List<CacheGitUser>>(_users)

    override suspend fun insertGitUserToDatabase(gitUser: List<CacheGitUser>?) {
        if (gitUser != null) {
            _users.addAll(gitUser)
        }
    }

    override suspend fun getGitUserFromDatabase(): LiveData<List<CacheGitUser>> {
        return obsUsers.apply {_users.sortBy { it.id }
        }
    }

}