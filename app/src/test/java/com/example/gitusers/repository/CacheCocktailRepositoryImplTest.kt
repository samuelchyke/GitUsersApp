package com.itc.cocktailapp.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.itc.cocktailapp.cache.CocktailDatabase
import com.itc.cocktailapp.cache.CocktailsDAO
import com.itc.cocktailapp.model.CacheCocktails
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CacheCocktailRepositoryImplTest {

    private lateinit var repository: CacheCocktailRepositoryImpl
    private lateinit var db: CocktailDatabase
    private lateinit var cocktailsDao: CocktailsDAO
    private lateinit var cocktails: List<CacheCocktails>

    @Before
    fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, CocktailDatabase::class.java)
            .allowMainThreadQueries().build()

        cocktailsDao = db.cocktailsDAO()

        repository = CacheCocktailRepositoryImpl(cocktailsDao)

        cocktails = listOf(
            CacheCocktails(
                "Test Cocktail",
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
                "",
                "",
                "",
                "",
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

        repository.insertCocktailsToDatabase(cocktails)

        val byLetter = repository.getCocktailsFromDatabase("")

        byLetter.observeForever {
            assertThat(cocktails).isEqualTo(it)
        }

    }

    @Test
    fun `get cocktails from database _ not null`() = runBlocking{

        val byLetter = repository.getCocktailsFromDatabase("")

        assertThat(byLetter).isNotNull()

    }
}