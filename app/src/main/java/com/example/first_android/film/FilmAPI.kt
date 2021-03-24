package com.example.first_android.film

import com.example.first_android.respon_data.ResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FilmAPI {

    @GET("film")
    fun getAllFilm(): Call<ResponseData>

    @GET("film/{film_id}")
    fun getFilmByID(@Path("film_id") id: String): Call<ResponseData>

    @POST("film")
    fun createFilm(@Body film: Film): Call<ResponseData>
}
