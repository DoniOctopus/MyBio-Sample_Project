package com.example.first_android.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.first_android.config.RetrofitBuilder

class FilmViewModel : ViewModel() {


    private val filmRepository: FilmRepository

    init {
        val filmAPI = RetrofitBuilder.createRetrofit().create(FilmAPI::class.java)
        filmRepository = FilmRepository(filmAPI)
    }

    val film: LiveData<Film> = filmRepository.film
    val filmList: LiveData<List<Film>> = filmRepository.filmList

    fun getAllFilm() {
        filmRepository.getAllFilm()
    }

    fun getFilmByID(filmID: String) {
        filmRepository.getFilmByID(filmID)
    }

    fun createFilm(film: Film) {
        filmRepository.saveFilm(film)
    }

}