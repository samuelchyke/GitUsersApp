package com.example.gitusers.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitusers.cache.GitUserDAO
import com.example.gitusers.cache.GitUserDatabase
import com.example.gitusers.model.CacheGitUser
import com.example.gitusers.repositories.CacheGitUserRepositoryImpl
import com.example.gitusers.repositories.GitUsersRepositoryImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CacheGitUserRepositoryImplTest {

    private lateinit var repository: CacheGitUserRepositoryImpl
    private lateinit var db: GitUserDatabase
    private lateinit var gitUserDao: GitUserDAO
    private lateinit var cocktails: List<CacheGitUser>

    @Before
    fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, GitUserDatabase::class.java)
            .allowMainThreadQueries().build()

        gitUserDao = db.gitUserDAO()

        repository = CacheGitUserRepositoryImpl(gitUserDao)

        cocktails = listOf(
            CacheGitUser(
                0,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
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
        )

    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `insert cocktails from database _ test cocktail`() = runBlocking {

        repository.insertGitUserToDatabase(cocktails)

        val byLetter = repository.getGitUserFromDatabase()

        byLetter.observeForever {
            assertThat(cocktails).isEqualTo(it)
        }

    }

    @Test
    fun `get cocktails from database _ not null`() = runBlocking{

        val byLetter = repository.getGitUserFromDatabase()

        assertThat(byLetter).isNotNull()

    }
}