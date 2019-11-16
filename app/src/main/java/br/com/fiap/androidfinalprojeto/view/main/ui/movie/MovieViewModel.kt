package br.com.fiap.androidfinalprojeto.view.main.ui.movie

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.room.model.Movie
import br.com.fiap.androidfinalprojeto.room.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel(application: Application, private val movieRepository: MovieRepository) : AndroidViewModel(application) {

    var movie: Movie = Movie()

    fun loadMovie(id: Long) {
        val auxMovie = movieRepository.allMovies.value?.filter { it.id == id }?.firstOrNull()
        if(auxMovie==null)
            throw  Exception(Resources.getSystem().getString(R.string.item_not_found))
        movie = movie
    }

    fun saveChanges() = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.insert(movie)
    }

}