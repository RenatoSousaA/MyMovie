package br.com.fiap.androidfinalprojeto.view.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.fiap.androidfinalprojeto.model.Movie
import br.com.fiap.androidfinalprojeto.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application, private val movieRepository: MovieRepository) : AndroidViewModel(application) {

    val allMovies: LiveData<List<Movie>> = movieRepository.allMovies

    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.insert(movie)
    }

    fun delete(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.delete(movie)
    }

}