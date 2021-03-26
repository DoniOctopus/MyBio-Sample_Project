package com.example.first_android.screens.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.first_android.data.domain.Film
import com.example.first_android.repository.FilmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    private var _allFilmLiveData = MutableLiveData<List<Film>>()
    val allFilmLiveData: LiveData<List<Film>>
        get() {
            return _allFilmLiveData
        }

    private var _filmLiveData = MutableLiveData<Film>()
    val filmLiveData: LiveData<Film>
        get() {
            return _filmLiveData
        }

    fun getAllFilm() {
        viewModelScope.launch(Dispatchers.IO) {
            _allFilmLiveData.postValue(filmRepository.getAllFilm())
        }

    }

    fun getFilmByID(filmID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            filmRepository.getFilmByID(filmID)?.let {
                _filmLiveData.postValue(it)
            }

        }
    }

    fun refreshFilm() {
        viewModelScope.launch(Dispatchers.IO) {
            filmRepository.refreshFilm()
            getAllFilm()
        }
    }
}