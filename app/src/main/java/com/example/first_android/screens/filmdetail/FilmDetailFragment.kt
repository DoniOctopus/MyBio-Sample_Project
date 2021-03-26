package com.example.first_android.screens.filmdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.first_android.R
import com.example.first_android.data.database.FilmDatabase
import com.example.first_android.data.network.RetrofitBuilder
import com.example.first_android.databinding.FragmentFilmDetailBinding
import com.example.first_android.repository.FilmRepositoryImpl
import com.example.first_android.screens.film.FilmViewModel
import com.squareup.picasso.Picasso

class FilmDetailFragment : Fragment() {
    private lateinit var filmDetailViewModel: FilmDetailViewModel
    private lateinit var binding: FragmentFilmDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        filmDetailViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val db = FilmDatabase.getDatabase(requireContext())
                val filmDao = db.filmDao()
                val filmService = RetrofitBuilder.filmService
                return FilmDetailViewModel(FilmRepositoryImpl(filmDao, filmService)) as T
            }

        }).get(FilmDetailViewModel::class.java)
    }

    private fun subscribe() {
        filmDetailViewModel.filmLiveData.observe(viewLifecycleOwner) {
            binding.durationFilmDetail.text = it.filmDuration
            binding.synopsisFilmDetail.text = it.filmSynopsis
            Picasso.get().load(it.filmImageUrl).into(binding.imageFilmDetail)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
        val filmID = arguments?.getString("filmID") ?: ""
        filmDetailViewModel.getFilmByID(filmID)
    }
}