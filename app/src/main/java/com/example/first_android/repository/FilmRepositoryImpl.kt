package com.example.first_android.repository

import com.example.first_android.data.database.dao.FilmDao
import com.example.first_android.data.database.entities.asDomainModel
import com.example.first_android.data.domain.Film
import com.example.first_android.data.network.FilmService
import com.example.first_android.data.network.asDatabaseModel

class FilmRepositoryImpl(private val filmDao: FilmDao, private val filmAPI: FilmService) :
    FilmRepository {

    override suspend fun getAllFilm() = filmDao.getAllFilm().asDomainModel()

    override suspend fun getFilmByID(filmID: String): Film? {
        val filmResponse = filmAPI.getFilmByID(filmID)
        if (filmResponse.isSuccessful) {
            filmResponse.body()?.let {
                val film = it.results
                filmDao.insert(film.asDatabaseModel())
                return filmDao.getFilm(filmID).asDomainModel()
            } ?: run {
                return null
            }
        } else {
            return null
        }
    }

    override suspend fun refreshFilm() {
        val filmResponse = filmAPI.getAllFilm()
        if (filmResponse.isSuccessful) {
            filmResponse.body()?.let {
                val films = it.results
                filmDao.insertAll(films.asDatabaseModel())
            }
        }
    }

}