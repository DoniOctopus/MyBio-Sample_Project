package com.example.first_android.data.network

import com.example.first_android.data.database.entities.FilmEntity

data class FilmResponse(
    val id: String,
    val originalTitle: String,
    val title: String,
    val duration: String,
    val imageUrl: String,
    val synopsis: String
)

fun FilmResponse.asDatabaseModel(): FilmEntity {
    return FilmEntity(
        filmId = this.id,
        filmTitle = this.originalTitle,
        filmDuration = this.duration,
        filmImageUrl = this.imageUrl,
        filmSynopsis = this.synopsis
    )
}

fun List<FilmResponse>.asDatabaseModel(): List<FilmEntity> {
    return map {
        FilmEntity(
            filmId = it.id,
            filmTitle = it.originalTitle,
            filmDuration = it.duration,
            filmImageUrl = it.imageUrl,
            filmSynopsis = it.synopsis
        )
    }
}