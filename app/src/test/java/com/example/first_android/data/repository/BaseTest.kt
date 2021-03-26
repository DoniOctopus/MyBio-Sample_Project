package com.example.first_android.data.repository

import com.example.first_android.data.database.dao.FilmDao
import com.example.first_android.data.database.entities.FilmEntity
import com.example.first_android.data.domain.Film
import com.example.first_android.data.network.FilmResponse
import com.example.first_android.data.network.FilmService
import com.example.first_android.data.network.ResponseData
import okhttp3.ResponseBody
import retrofit2.Response

abstract class BaseTest {
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
    val dummyFilmDao = object : FilmDao {
        override fun getAllFilm() = dummyFilmEntity

        override fun getFilm(id: String) = dummyFilmEntity[0]

        override suspend fun insert(vararg obj: FilmEntity) {

        }

        override fun insertAll(videos: List<FilmEntity>) {
            dummyList = 1
        }

        override suspend fun update(obj: FilmEntity) {
        }

        override suspend fun delete(obj: FilmEntity) {
        }

    }

    val dummyFilmApi = object : FilmService {
        override suspend fun getAllFilm(): Response<ResponseData<List<FilmResponse>>> {
            return Response.success(dummyResponseData)
        }

        override suspend fun getFilmByID(id: String): Response<ResponseData<FilmResponse>> {
            return if (id == "123") {
                Response.success(dummyResponseSingleData)
            } else {
                Response.error(404, ResponseBody.create(null, ""))
            }
        }

        override suspend fun createFilm(film: Film): Response<ResponseData<Film>> {
            TODO("Not yet implemented")
        }

    }

}