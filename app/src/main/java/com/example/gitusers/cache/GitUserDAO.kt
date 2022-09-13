package com.example.gitusers.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gitusers.model.CacheGitUser

@Dao
interface GitUserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGitUsers(cocktails: List<CacheGitUser>?)

    @Query("SELECT * FROM user_table")
    fun getGitUsers(): LiveData<List<CacheGitUser>>

    @Query("SELECT * FROM user_table WHERE login LIKE  :letter || '%' ")
    fun searchGitUsers(letter: String): LiveData<List<CacheGitUser>>

}