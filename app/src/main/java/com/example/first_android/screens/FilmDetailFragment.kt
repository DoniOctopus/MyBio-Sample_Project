package com.example.first_android.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.first_android.R
import com.example.first_android.film.FilmViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_film_detail.*

class FilmDetailFragment : Fragment() {

    private val filmViewModel by activityViewModels<FilmViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filmID = arguments?.getString("filmID") ?: ""
        filmViewModel.getFilmByID(filmID)
        filmViewModel.film.observe(viewLifecycleOwner, Observer {
            duration_film_detail.text = it.film_duration
            synopsis_film_detail.text = it.film_synopsis
            Picasso.get().load(it.film_image_url).into(image_film_detail)
        })
    }
}