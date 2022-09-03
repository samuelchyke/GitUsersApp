package com.example.gitusers.cache

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gitusers.model.CacheGitUser
import com.example.gitusers.model.GitUser

@Dao
interface GitUserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGitUsers(cocktails: List<CacheGitUser>?)

    @Query("SELECT * FROM user_table")
    fun getGitUsers(): LiveData<List<CacheGitUser>>

    @Query("SELECT * FROM user_table WHERE login LIKE  :letter || '%' ")
    fun searchGitUsers(letter: String): LiveData<List<CacheGitUser>>

    // Testing

//    @Insert
//    fun insertTestCocktail(cocktails: CacheCocktails)
//
//    @Query("SELECT * FROM cocktails_table ORDER BY strDrink ASC ")
//    fun getTestCocktail(): CacheCocktails
}