package br.com.fiap.androidfinalprojeto.view.main.ui.all_movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.room.model.Movie
import br.com.fiap.androidfinalprojeto.util.ResourceStringUtil.Companion.enforceGetString



class AllMoviesListAdapter internal constructor(val father: AllMoviesFragment) : RecyclerView.Adapter<AllMoviesListAdapter.AllMoviesViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(father.context)
    private var movies = emptyList<Movie>() // Cached copy of words

    inner class AllMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Adaptação implementada aqui
        val movieName: TextView = itemView.findViewById(R.id.amrvMovieName)
        val movieRating: TextView = itemView.findViewById(R.id.movieRating)
        val btnShare: Button = itemView.findViewById(R.id.btnShared)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMoviesViewHolder {
        val itemView = inflater.inflate(R.layout.all_movies_recyclerview, parent, false)
        return AllMoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AllMoviesViewHolder, position: Int) {

        val movie = movies[position]

        var name = movie.name
        var rating = movie.score.toString()
        var description = movie.description

        holder.movieName.text = name
        holder.movieRating.text = rating

        holder.itemView.setOnClickListener {
            father.openMovieToEdit(movie.id)
        }

        holder.btnShare.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(
                Intent.EXTRA_TEXT,  R.string.share_msg.enforceGetString(father.activity).replace("{name}",name).replace("{rating}",rating).replace("{description}", description))
            intent.type = "text/plain"
            father.context?.startActivity(Intent.createChooser(intent, R.string.send_to.enforceGetString(father.activity)))
        })

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