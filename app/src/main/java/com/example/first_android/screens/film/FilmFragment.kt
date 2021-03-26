package com.example.first_android.screens.film

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.first_android.data.database.FilmDatabase
import com.example.first_android.data.network.RetrofitBuilder
import com.example.first_android.databinding.FragmentFilmBinding
import com.example.first_android.film.FilmRecycleAdapter
import com.example.first_android.repository.FilmRepositoryImpl

class FilmFragment : Fragment() {

    private lateinit var filmViewModel: FilmViewModel
    private lateinit var filmRecycleAdapter: FilmRecycleAdapter
    private lateinit var binding: FragmentFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        filmViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val db = FilmDatabase.getDatabase(requireContext())
                val filmDao = db.filmDao()
                val filmService = RetrofitBuilder.filmService
                return FilmViewModel(FilmRepositoryImpl(filmDao, filmService)) as T
            }

        }).get(FilmViewModel::class.java)
    }

    private fun subsribe() {
        filmViewModel.allFilmLiveData.observe(viewLifecycleOwner, Observer {
            filmRecycleAdapter = FilmRecycleAdapter(it)
            binding.filmsRecycleView.adapter = filmRecycleAdapter
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subsribe()
        binding?.apply {
            filmsRecycleView.layoutManager = LinearLayoutManager(requireContext())
            filmViewModel.refreshFilm()
        }
    }

}