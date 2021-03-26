package com.example.first_android.film

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.first_android.R
import com.example.first_android.data.domain.Film
import com.example.first_android.databinding.FilmListItemBinding
import com.squareup.picasso.Picasso

class FilmRecycleAdapter(private val filmList: List<Film>) :
    RecyclerView.Adapter<FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding =
            FilmListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bindData(filmList[position])
    }
}

class FilmViewHolder(private val binding: FilmListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(film: Film) {
        if (film.filmImageUrl.isEmpty()) {
            binding.imageFilm.setImageResource(R.drawable.ic_baseline_add_photo_alternate_24);
        } else {
            Picasso.get().load(film.filmImageUrl).into(binding.imageFilm)
            binding.titleTextView.text = film.filmTitle
        }
        binding.itemView.setOnClickListener {
            val bundle = bundleOf(Pair("filmID", film.filmId))
            Navigation.findNavController(it)
                .navigate(R.id.action_filmFragment_to_filmDetailFragment, bundle)
        }
    }
}