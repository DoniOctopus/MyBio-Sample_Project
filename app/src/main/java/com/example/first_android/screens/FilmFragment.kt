package com.example.first_android.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.first_android.R
import com.example.first_android.film.FilmRecycleAdapter
import com.example.first_android.film.FilmViewModel
import kotlinx.android.synthetic.main.fragment_film.*

class FilmFragment : Fragment() {

    private val filmViewModel by activityViewModels<FilmViewModel>()
    private lateinit var filmRecycleAdapter: FilmRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridRecycleView = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        filmsRecycleView.layoutManager = gridRecycleView
        filmViewModel.getAllFilm()
        filmViewModel.filmList.observe(viewLifecycleOwner, Observer {
            filmRecycleAdapter = FilmRecycleAdapter(it)
            filmsRecycleView.adapter = filmRecycleAdapter
        })
    }

}