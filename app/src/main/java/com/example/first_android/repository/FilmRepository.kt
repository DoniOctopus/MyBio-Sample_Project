package com.example.first_android.repository

import com.example.first_android.data.domain.Film

interface FilmRepository {
    suspend fun getAllFilm(): List<Film>
    suspend fun getFilmByID(filmID: String): Film?
    suspend fun refreshFilm()
}