package com.example.gitusers

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitusers.api.GitServiceApi
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class GitServiceApiTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: GitServiceApi
    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(GitServiceApi::class.java)
    }

    @Test
    fun`get list of cocktails api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = apiService.getUsers()
            val request = mockWebServer.takeRequest()
            assertEquals("/users",request.path)
            assertThat(true).isEqualTo(response.body()?.isEmpty())
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}