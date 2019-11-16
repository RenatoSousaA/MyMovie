package br.com.fiap.androidfinalprojeto.view.main.ui.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.room.model.Movie
import br.com.fiap.androidfinalprojeto.room.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import br.com.fiap.androidfinalprojeto.util.ResourceStringUtil.Companion.enforceGetString

class MovieViewModel(application: Application, private val movieRepository: MovieRepository) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var movie: Movie = Movie()

    fun loadMovie(id: Long) {
        val auxMovie = movieRepository.allMovies.value?.filter { it.id == id }?.firstOrNull()
        if(auxMovie==null) {
            throw  Exception(R.string.item_not_found.enforceGetString(context))
        }
        movie = auxMovie
    }

    fun saveChanges() = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.insert(movie)
    }

}