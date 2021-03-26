package com.example.first_android.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.first_android.data.database.entities.FilmEntity

@Dao
interface FilmDao : BaseDao<FilmEntity> {
    @Query("SELECT * FROM m_film")
    fun getAllFilm(): List<FilmEntity>

    @Query("SELECT * FROM m_film WHERE film_id=:id")
    fun getFilm(id: String): FilmEntity
}