package com.itc.cocktailapp.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.itc.cocktailapp.api.CocktailServiceApi
import com.itc.cocktailapp.model.Cocktails
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
class CocktailRepositoryImplTest {

    private lateinit var repository : CocktailRepositoryImpl

    @Mock
    lateinit var api: CocktailServiceApi

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = CocktailRepositoryImpl(api)
    }

    @Test
    fun `get cocktails by letter _ cocktail with empty list of drinks`() {
        runBlocking {
            Mockito.`when`(api.getCocktails("A")).thenReturn(Response.success(listOf(Cocktails(listOf()))))
            val response = repository.getCocktails("A")
            assertThat(Cocktails(listOf())).isEqualTo((response.data))
        }
    }
}