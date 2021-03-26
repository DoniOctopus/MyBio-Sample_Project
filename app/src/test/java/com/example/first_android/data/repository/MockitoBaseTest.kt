package com.example.first_android.data.repository

import com.example.first_android.data.database.dao.FilmDao
import com.example.first_android.data.database.entities.FilmEntity
import com.example.first_android.data.network.FilmResponse
import com.example.first_android.data.network.FilmService
import com.example.first_android.data.network.ResponseData
import com.example.first_android.repository.FilmRepositoryImpl
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class MockitoBaseTest {
    companion object {
        var dummyList = 0
    }
    val dummyFilmEntity = listOf(
        FilmEntity(
            filmId = "123",
            filmTitle = "Dummy Film",
            filmDuration = "0 Minute",
            filmSynopsis = "This is dummy film",
            filmImageUrl = ""
        )
    )

    val dummyFilmResponse = listOf(
        FilmResponse(
            id = "123",
            originalTitle = "Dummy Film",
            title = "Dummy Film",
            duration = "0 Minute",
            synopsis = "This is dummy film",
            imageUrl = ""
        )
    )
    val dummyResponseData = ResponseData(
        status = "Ok",
        message = "",
        results = dummyFilmResponse
    )

    val dummyResponseSingleData = ResponseData(
        status = "Ok",
        message = "",
        results = dummyFilmResponse[0]
    )

    @Mock
    var dummyFilmDao: FilmDao? = null

    @Mock
    var dummyFilmApi: FilmService? = null

    @Before
    fun registerLogMock() {
        MockitoAnnotations.openMocks(this)
    }
}