package br.com.fiap.androidfinalprojeto.view.main.ui.all_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.fiap.androidfinalprojeto.R

class AllMoviesFragment : Fragment() {

    private lateinit var allMoviesViewModel: AllMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allMoviesViewModel =
            ViewModelProviders.of(this).get(AllMoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all_movies, container, false)
        val textView: TextView = root.findViewById(R.id.text_tools)
        allMoviesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    companion object {
        const val EXTRA_MOVIEID = "br.com.fiap.androidfinalprojeto.allmoviesfragment.EXTRA_MOVIEID"
    }
}