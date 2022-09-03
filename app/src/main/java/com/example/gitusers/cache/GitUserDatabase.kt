package com.example.gitusers.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gitusers.model.CacheGitUser

@Database(entities = [CacheGitUser::class], version = 1, exportSchema = false )
abstract class GitUserDatabase : RoomDatabase() {

    abstract fun gitUserDAO(): GitUserDAO

}