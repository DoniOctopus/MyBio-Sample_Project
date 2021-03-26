package com.example.first_android.data.repository

import com.example.first_android.repository.FilmRepositoryImpl
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Test
import org.mockito.Mockito.`when`
import retrofit2.Response

class FilmRespositoryTest : MockitoBaseTest() {

    @Test
    fun filmRepository_onGetAllFilm_returnListFilm() {
        runBlocking {
            val repositoryDummySuccess = FilmRepositoryImpl(dummyFilmDao!!, dummyFilmApi!!)
            `when`(dummyFilmDao!!.getAllFilm()).thenReturn(dummyFilmEntity)
            val actualResult = repositoryDummySuccess.getAllFilm()
            Truth.assertThat(actualResult.size).isEqualTo(1)
            Truth.assertThat(actualResult[0].filmId).isEqualTo("123")
        }
    }

    @Test
    fun filmRespository_onGetFilmById_returnFilm() {
        runBlocking {
            val repositoryDummySuccess = FilmRepositoryImpl(dummyFilmDao!!, dummyFilmApi!!)
            `when`(dummyFilmApi!!.getFilmByID("123")).thenReturn(
                Response.success(
                    dummyResponseSingleData
                )
            )
            `when`(dummyFilmDao!!.getFilm("123")).thenReturn(dummyFilmEntity[0])
            val actualResult = repositoryDummySuccess.getFilmByID("123")
            Truth.assertThat(actualResult).isNotNull()
            actualResult?.let {
                Truth.assertThat(it.filmId).isEqualTo("123")
            }
        }
    }

    @Test
    fun filmRespository_onGetFilmById_whenNotExistReturnNull() {
        runBlocking {
            val repositoryDummyFailed = FilmRepositoryImpl(dummyFilmDao!!, dummyFilmApi!!)
            `when`(dummyFilmApi!!.getFilmByID("000")).thenReturn(
                Response.error(404, ResponseBody.create(null, ""))
            )
            `when`(dummyFilmDao!!.getFilm("000")).thenReturn(null)
            val actualResult = repositoryDummyFailed.getFilmByID("000")
            Truth.assertThat(actualResult).isNull()
        }
    }

    @Test
    fun filmRepository_onRefreshFilm_whenSuccess() {
        runBlocking {
            var insertAllIsCalled = 0
            val repositoryDummySuccess = FilmRepositoryImpl(dummyFilmDao!!, dummyFilmApi!!)
            `when`(dummyFilmApi!!.getAllFilm()).thenReturn(Response.success(dummyResponseData))
            `when`(dummyFilmDao!!.insertAll(dummyFilmEntity)).then {
                insertAllIsCalled = 1
                insertAllIsCalled
            }
            repositoryDummySuccess.refreshFilm()
            Truth.assertThat(insertAllIsCalled).isEqualTo(1)
        }
    }
}