package br.com.fiap.androidfinalprojeto.view.main.ui.all_movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.room.model.Movie


class AllMoviesListAdapter internal constructor(val father: AllMoviesFragment) : RecyclerView.Adapter<AllMoviesListAdapter.AllMoviesViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(father.context)
    private var movies = emptyList<Movie>() // Cached copy of words

    inner class AllMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Adaptação implementada aqui
        val movieName: TextView = itemView.findViewById(R.id.amrvMovieName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMoviesViewHolder {
        val itemView = inflater.inflate(R.layout.all_movies_recyclerview, parent, false)
        return AllMoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AllMoviesViewHolder, position: Int) {

        val movie = movies[position]

        holder.movieName.text = movie.name

        holder.itemView.setOnClickListener {
            father.openMovieToEdit(movie.id)
        }

    }

    internal fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun getItemCount() = movies.size

    fun getMovies(): List<Movie> {
        return this.movies
    }

 }