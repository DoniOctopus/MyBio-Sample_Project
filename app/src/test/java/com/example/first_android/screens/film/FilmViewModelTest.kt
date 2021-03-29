package com.example.first_android.screens.film

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.first_android.data.domain.Film
import com.example.first_android.repository.FilmRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class FilmViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<List<Film>>

    private lateinit var viewModel: FilmViewModel

    private val dummyFilm = listOf(
        Film(
            filmId = "123",
            filmTitle = "Dummy Film",
            filmDuration = "0 minute",
            filmImageUrl = "",
            filmSynopsis = "It's dummy film"
        )
    )


    @Before
    fun initViewModel() {
        MockitoAnnotations.openMocks(this)
        viewModel = FilmViewModel(filmRepository)
    }

    @Test
    fun getAllFilm_setListFilmEvent() {
        runBlocking {
            Mockito.`when`(filmRepository.getAllFilm()).thenReturn(dummyFilm)
            viewModel.allFilmLiveData.observeForever(observer)
            viewModel.getAllFilm()
            verify(observer).onChanged(dummyFilm)
            val actualResult = viewModel.allFilmLiveData.value
            Truth.assertThat(actualResult).isNotNull()
            Truth.assertThat(actualResult!![0].filmId).isEqualTo("123")
            viewModel.allFilmLiveData.removeObserver(observer)
        }
    }

    @Test
    fun getRefreshFilm_refreshList() {
        runBlocking {
            Mockito.`when`(filmRepository.refreshFilm()).thenReturn(Unit)
            Mockito.`when`(filmRepository.getAllFilm()).thenReturn(dummyFilm)

            viewModel.allFilmLiveData.observeForever(observer)
            viewModel.refreshFilm()
            verify(filmRepository).refreshFilm()
            verify(observer).onChanged(dummyFilm)
            viewModel.allFilmLiveData.removeObserver(observer)
        }
    }
}