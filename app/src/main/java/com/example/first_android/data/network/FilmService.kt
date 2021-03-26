package com.example.first_android.data.network

import com.example.first_android.data.domain.Film
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FilmService {
    @GET("/film")
    suspend fun getAllFilm(): Response<ResponseData<List<FilmResponse>>>

    @GET("/film/{film_id}")
    suspend fun getFilmByID(@Path("film_id") id: String): Response<ResponseData<FilmResponse>>

    @POST("/film")
    suspend fun createFilm(@Body film: Film): Response<ResponseData<Film>>
}
