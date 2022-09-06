package com.example.gitusers.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitusers.api.GitServiceApi
import com.example.gitusers.model.GitUser
import com.example.gitusers.repositories.GitUsersRepositoryImpl
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class GitUserRepositoryImplTest {

    private lateinit var repository : GitUsersRepositoryImpl

    @Mock
    lateinit var api: GitServiceApi

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = GitUsersRepositoryImpl(api)
    }

    @Test
    fun `get users _ git users with empty list of drinks`() {
        runBlocking {
            Mockito.`when`(api.getUsers()).thenReturn(Response.success((listOf(
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
                )))))
            val response = repository.getUsers()
            assertThat((listOf(
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
                )))).isEqualTo((response.data))
        }
    }
}