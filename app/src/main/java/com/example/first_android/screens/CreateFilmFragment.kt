package com.example.first_android.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.first_android.R
import com.example.first_android.film.Film
import com.example.first_android.film.FilmViewModel
import kotlinx.android.synthetic.main.fragment_create_film.*

class CreateFilmFragment : Fragment() {

    private val filmViewModel by activityViewModels<FilmViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitFilm.setOnClickListener {
            val newFilm = Film(film_title = filmTitleTextInput.text.toString()
                ,film_duration = filmDurationTextInput.text.toString()
                ,film_image_url = filmImageURLtextInput.text.toString()
                ,film_synopsis = filmSynopsisTextInput.text.toString())
            filmViewModel.createFilm(newFilm)
            Navigation.findNavController(view).navigate(R.id.action_createFilmFragment_pop)
        }
    }

}