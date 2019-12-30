package br.com.fiap.androidfinalprojeto.room.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import br.com.fiap.androidfinalprojeto.room.dao.MovieDao
import br.com.fiap.androidfinalprojeto.room.model.Movie

class MovieRepository(private val movieDao: MovieDao) {

    val allMovies: LiveData<List<Movie>> = movieDao.getAll()

    @WorkerThread//Anotação para rodar em background
    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    @WorkerThread//Anotação para rodar em background
    suspend fun delete(movie: Movie) {
        movieDao.delete(movie)
    }

}