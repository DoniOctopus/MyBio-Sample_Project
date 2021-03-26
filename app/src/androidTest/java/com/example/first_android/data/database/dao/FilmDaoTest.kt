package com.example.first_android.data.database.dao

import com.example.first_android.BaseTest
import com.example.first_android.data.database.entities.FilmEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FilmDaoTest : BaseTest() {
    val dummyFilm = FilmEntity(
        filmId = "123",
        filmTitle = "Dummy Film",
        filmDuration = "0 Minute",
        filmSynopsis = "This is dummy film",
        filmImageUrl = ""
    )

    fun createSampleData() {
        myDatabase?.let {
            runBlocking {
                it.filmDao().insert(dummyFilm)
            }
        }
    }

    @Test
    fun filmDao_onGetAllFilm_returnListFilm() {
        createSampleData()
        myDatabase?.let {
            val actualResult = it.filmDao().getAllFilm()
            Truth.assertThat(actualResult.size).isEqualTo(1)
            Truth.assertThat(actualResult[0].filmId).isEqualTo("123")
        }
    }

    @Test
    fun filmDao_onGetFilm_returnFilm() {
        createSampleData()
        myDatabase?.let {
            val actualResult = it.filmDao().getFilm("123")
            Truth.assertThat(actualResult.filmTitle).isEqualTo("Dummy Film")
        }
    }
}

