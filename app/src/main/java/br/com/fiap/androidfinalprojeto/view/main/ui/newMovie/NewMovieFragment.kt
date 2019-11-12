package br.com.fiap.androidfinalprojeto.view.main.ui.newMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.fiap.androidfinalprojeto.R

class NewMovieFragment : Fragment() {

    private lateinit var newMovieViewModel: NewMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newMovieViewModel =
            ViewModelProviders.of(this).get(NewMovieViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_movie, container, false)
        val textView: TextView = root.findViewById(R.id.text_new_movie)
        newMovieViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}