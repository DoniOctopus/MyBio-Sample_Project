package com.example.first_android.screens.filmdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.first_android.data.domain.Film
import com.example.first_android.repository.FilmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmDetailViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    private var _filmLiveData = MutableLiveData<Film>()
    val filmLiveData: LiveData<Film>
        get() {
            return _filmLiveData
        }

    fun getFilmByID(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            filmRepository.getFilmByID(id)?.let {
                _filmLiveData.postValue(it)
            }
        }
    }
}