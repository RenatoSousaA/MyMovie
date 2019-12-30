package br.com.fiap.androidfinalprojeto.view.main.ui.all_movie

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.view.main.ui.movie.MovieFragment
import br.com.fiap.androidfinalprojeto.view.main.ui.movie.MovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.ItemTouchHelper
import br.com.fiap.androidfinalprojeto.room.repository.MovieRepository
import br.com.fiap.androidfinalprojeto.util.EditTextUtil.Companion.validate
import com.google.android.material.snackbar.Snackbar
import br.com.fiap.androidfinalprojeto.util.SwipeToDeleteCallback
import br.com.fiap.androidfinalprojeto.view.main.MainActivity
import kotlinx.android.synthetic.main.all_movies_recyclerview.*
import kotlinx.android.synthetic.main.all_movies_recyclerview.view.*
import kotlinx.android.synthetic.main.fragment_all_movies.*
import kotlinx.android.synthetic.main.fragment_all_movies.view.*
import java.lang.Exception
import java.util.*

class AllMoviesFragment : Fragment() {

    //Usando Koin para injetar dependencia
    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var adapter: AllMoviesListAdapter
    private lateinit var movieRepository: MovieRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Container do fragment
        val root = inflater.inflate(R.layout.fragment_all_movies, container, false)

        //Setup list
        adapter = AllMoviesListAdapter(this)
        val recyclerview: RecyclerView = root.findViewById(R.id.allMoviesRecyclerview)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(activity)

        //Observer
        movieViewModel.allMovies.observe(this, Observer { movies ->
            // Update the cached copy of the movies in the adapter

            val name: String? = arguments?.getString(EXTRA_MOVIEID)

            if (name != "") {
                var moviesFilter = movies.filter { it.name.toLowerCase().contains(name?.toLowerCase() ?: "")}
                moviesFilter?.let { adapter.setMovies(it) }

                if (moviesFilter.count() == 0) {
                    tv_notFound.visibility = View.VISIBLE
                } else {
                    tv_notFound.visibility = View.INVISIBLE
                }
            } else {
                movies?.let { adapter.setMovies(it) }

                if (movies.count() == 0) {
                    tv_notFound.visibility = View.VISIBLE
                } else {
                    tv_notFound.visibility = View.INVISIBLE
                }
            }

        })

        enableSwipeToDeleteAndUndo(recyclerview)

        return root
    }

    //Adaptar delega para o pai, devido ao context não possuir o fragmentManager
    fun openMovieToEdit(id: Long) {

        //Redireciona para edição
        val f = MovieFragment()
        val args = Bundle()
        args.putLong(MovieFragment.EXTRA_MOVIEID, id)
        f.arguments = args

        //Troca de fragmento
        val ft = fragmentManager?.beginTransaction()
        ft?.replace(br.com.fiap.androidfinalprojeto.R.id.nav_host_fragment, f)
        ft?.addToBackStack(null)
        ft?.commit()

    }

    private fun enableSwipeToDeleteAndUndo(recyclerview: RecyclerView) {

        val swipeToDeleteCallback = object : SwipeToDeleteCallback(activity?.baseContext!!) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {

                //Remove item
                val position = viewHolder.adapterPosition
                val movie = adapter.getMovies().get(position)
                movieViewModel.delete(movie)

                val snackbar = Snackbar.make(recyclerview, R.string.movie_was_removed, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.undo) {
                    movieViewModel.insert(movie)
                    recyclerview.scrollToPosition(position)
                }

                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()

            }
        }

        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(recyclerview)

    }

    companion object {
        const val EXTRA_MOVIEID = "br.com.fiap.androidfinalprojeto.allmoviesfragment.EXTRA_MOVIEID"
    }
}