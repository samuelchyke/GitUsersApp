package com.example.gitusers.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitusers.model.GitUser
import com.example.gitusers.repositories.GitUsersRepository
import com.example.gitusers.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class GitViewModel @Inject constructor(
    private val gitUserRepository : GitUsersRepository,
    @ApplicationContext private val context: Context
) : ViewModel(){

    private val _users: MutableLiveData<NetworkResult<GitUser>> = MutableLiveData()
    val users: LiveData<NetworkResult<GitUser>> get() = _users

    public fun getUsers() {
        safeCallUsersFromNetwork()
    }

    private fun safeCallUsersFromNetwork() =
        // RUN SCOPE ON MAIN THREAD
        viewModelScope.launch {
            _users.postValue(NetworkResult.Loading())
            try {
                // NETWORK CONNECTED : MAKE NETWORK CALL
                var response = gitUserRepository.getUsers()
                _users.postValue(response)
                // SAVE RESULTS TO DATABASE IF DRINKS IS NOT NULL
//                response.data?.drinks?.let {
//                    saveCocktailsToDataBase(it)
//                } ?: let {
//                    Toast.makeText(context, "No results", Toast.LENGTH_SHORT).show()
//                    response = cocktailRepository.getCocktails("A")
//                    _cocktails.postValue(response)
//                }
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> _users.postValue(NetworkResult.Error("Network Failure"))
                    else -> _users.postValue(NetworkResult.Error("Conversion Error"))
                }
            }
        }
}