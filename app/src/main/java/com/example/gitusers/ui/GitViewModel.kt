package com.example.gitusers.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitusers.model.GitUser
import com.example.gitusers.model.Search
import com.example.gitusers.model.mapToCache
import com.example.gitusers.repositories.CacheGitUserRepository
import com.example.gitusers.repositories.GitUsersRepository
import com.example.gitusers.utils.HandleGitUserResponse
import com.example.gitusers.utils.HandleSearchResponse
import com.example.gitusers.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class GitViewModel @Inject constructor(
    private val gitUserRepository : GitUsersRepository,
    private val cacheGitUserRepository : CacheGitUserRepository,
    private val handleGitUserResponse: HandleGitUserResponse,
    private val handleSearchResponse: HandleSearchResponse,
    @ApplicationContext private val context: Context
) : ViewModel(){

    private val _users: MutableLiveData<NetworkResult<List<GitUser>>> = MutableLiveData()
    val users: LiveData<NetworkResult<List<GitUser>>> get() = _users

    private val _searchedUsers: MutableLiveData<NetworkResult<Search>> = MutableLiveData()
    val searchedUsers: LiveData<NetworkResult<Search>> get() = _searchedUsers

    fun getUsers() = safeCallUsersFromNetwork()

    fun searchUsers(letter: String) = safeSearchUsersFromNetwork(letter)

    suspend fun getUsersFromDB() = cacheGitUserRepository.getGitUserFromDatabase()

    private fun safeCallUsersFromNetwork() =
        // RUN SCOPE ON MAIN THREAD
        viewModelScope.launch {
            _users.postValue(NetworkResult.Loading())
            try {
                // NETWORK CONNECTED : MAKE NETWORK CALL : HANDLE RESPONSE
                val response = handleGitUserResponse.handleResponse(
                    gitUserRepository.getUsers()
                )
                _users.postValue(response)
                // SAVE RESULTS TO DATABASE IF USERS IS NOT NULL
                response.data?.let {
                    saveGitUsersToDataBase(it)
                } ?: let {
                    Toast.makeText(context, "No results", Toast.LENGTH_SHORT).show()
                    _users.postValue(response)
                }
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> _users.postValue(NetworkResult.Error("Network Failure"))
                    else -> _users.postValue(NetworkResult.Error("Conversion Error"))
                }
            }
        }

        private fun safeSearchUsersFromNetwork(letter:String) =
        // RUN SCOPE ON MAIN THREAD
        viewModelScope.launch {
            _users.postValue(NetworkResult.Loading())
            try {
                // NETWORK CONNECTED : MAKE NETWORK CALL
                val response = handleSearchResponse.handleResponse(
                    gitUserRepository.searchUsers(letter)
                )
                _searchedUsers.postValue(response)
                // SAVE RESULTS TO DATABASE IF USERS IS NOT NULL
                response.data?.let {
                    saveGitUsersToDataBase(it.items)
                } ?: let {
                    Toast.makeText(context, "No results", Toast.LENGTH_SHORT).show()
                }
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> _users.postValue(NetworkResult.Error("Network Failure"))
                    else -> _users.postValue(NetworkResult.Error("Conversion Error"))
                }
            }
        }

    private fun saveGitUsersToDataBase(users: List<GitUser>?) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val convert = users?.mapToCache()
            cacheGitUserRepository.insertGitUserToDatabase(convert)
        }
    }

}