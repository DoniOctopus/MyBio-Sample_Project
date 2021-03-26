package com.example.first_android.data.network

import com.example.first_android.BaseTest
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FilmServiceTest : BaseTest() {
    override fun isMockServerEnabled() = true

    override fun isMockDatabaseEnabled() = false

    @Test
    fun filmService_onGetAllFilm_successReturnList() {
        runBlocking {
            mockServer?.let {
                val filmApi = getRetrofit(it).create(FilmService::class.java)
                it.enqueue(getResponse("getAllFilm_whenSuccess.json"))

                val actualResponse = filmApi.getAllFilm()
                Truth.assertThat(actualResponse.isSuccessful).isEqualTo(true)
                val actualBody = actualResponse.body()!!
                val actualResult = actualBody.results
                Truth.assertThat(actualResult.size).isEqualTo(1)
            }
        }
    }

    @Test
    fun filmService_onGetFilmById_successReturnFilm() {
        runBlocking {
            mockServer?.let {
                val filmApi = getRetrofit(it).create(FilmService::class.java)
                it.enqueue(getResponse("getFilm_whenSuccess.json"))

                val actualResponse = filmApi.getFilmByID("123")
                Truth.assertThat(actualResponse.isSuccessful).isEqualTo(true)
                val actualBody = actualResponse.body()!!
                val actualResult = actualBody.results
                Truth.assertThat(actualResult.originalTitle).isEqualTo("Dummy Original Title")
            }
        }
    }
}