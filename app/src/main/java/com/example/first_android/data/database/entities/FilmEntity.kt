package com.example.first_android.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.first_android.data.domain.Film

@Entity(tableName = "m_film")
data class FilmEntity(
    @PrimaryKey
    @ColumnInfo(name = "film_id") val filmId: String,
    @ColumnInfo(name = "film_title") val filmTitle: String,
    @ColumnInfo(name = "film_duration") val filmDuration: String,
    @ColumnInfo(name = "film_image_url") val filmImageUrl: String,
    @ColumnInfo(name = "film_synopsis") val filmSynopsis: String
)

fun List<FilmEntity>.asDomainModel(): List<Film> {
    return map {
        Film(
            filmId = it.filmId,
            filmTitle = it.filmTitle,
            filmDuration = it.filmDuration,
            filmImageUrl = it.filmImageUrl,
            filmSynopsis = it.filmSynopsis
        )
    }
}

fun FilmEntity.asDomainModel(): Film {
    return Film(
        filmId = this.filmId,
        filmTitle = this.filmTitle,
        filmDuration = this.filmDuration,
        filmImageUrl = this.filmImageUrl,
        filmSynopsis = this.filmSynopsis
    )
}