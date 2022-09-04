package com.example.gitusers.ui

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitusers.cache.GitUserDAO
import com.example.gitusers.cache.GitUserDatabase
import com.google.common.truth.Truth.assertThat
import com.example.gitusers.repository.mocks.FakeCacheRepo
import com.example.gitusers.repository.mocks.FakeNetworkRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class GitViewModelTest{

    private lateinit var viewModel: GitViewModel
    private lateinit var db: GitUserDatabase
    private lateinit var cocktailsDao: GitUserDAO
    private lateinit var fakeCacheRepo: FakeCacheRepo
    private lateinit var fakeNetworkRepo: FakeNetworkRepo

    @Before
    fun setUp() {

        fakeNetworkRepo = FakeNetworkRepo()
        fakeCacheRepo = FakeCacheRepo()

        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, GitUserDatabase::class.java)
            .allowMainThreadQueries().build()

        cocktailsDao = db.gitUserDAO()

        viewModel = GitViewModel(FakeNetworkRepo(), FakeCacheRepo(), context)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        db.close()
    }

    @Test
    fun getUsersFromNetwork() {

        val a = viewModel.getUsers()

        viewModel.users.observeForever{
            assertThat(it).isEqualTo(a)
        }

    }

    @Test
    fun searchForUsersInNetwork() {

        val a = viewModel.searchUsers("A")

        viewModel.searchedUsers.observeForever{
            assertThat(it).isEqualTo(a)
        }

    }

    @Test
    fun `get cocktails from database _ not null`() = runTest {

        val dataFromDB = viewModel.getUsersFromDB()

        dataFromDB.observeForever {
            assertThat(it).isNotNull()
        }

    }

}