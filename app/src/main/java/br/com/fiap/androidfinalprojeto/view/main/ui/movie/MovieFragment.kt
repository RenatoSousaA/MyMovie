package br.com.fiap.androidfinalprojeto.view.main.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.view.main.ui.all_movie.AllMoviesFragment
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.util.*
import br.com.fiap.androidfinalprojeto.util.EditTextUtil.Companion.validate

class MovieFragment : Fragment() {

    //Usando Koin para injetar dependencia
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_new_movie, container, false)

        //Carrega itens do layout
        val title = root.findViewById<TextView>(R.id.text_new_movie)
        val btMovieSave = root.findViewById<Button>(R.id.btMovieSave)
        val etMovieName = root.findViewById<EditText>(R.id.etMovieName)
        val etMovieDescription = root.findViewById<EditText>(R.id.etMovieDescription)
        val etMovieCategory = root.findViewById<EditText>(R.id.etMovieCategory)
        val etMovieDisplayPlataform = root.findViewById<EditText>(R.id.etMovieDisplayPlataform)
        val rbMovieScore = root.findViewById<RatingBar>(R.id.rbMovieScore)
        val cvMovieDisplayDate = root.findViewById<CalendarView>(R.id.cvMovieDisplayDate)

        //Checa modo do fragmento, add ou edit
        title.setText(R.string.new_movie)
        btMovieSave.setText(R.string.Add)
        if(arguments !=null){
            val id: Long? = arguments?.getLong(EXTRA_MOVIEID)
            if(id!=null && id>0) {
                movieViewModel.loadMovie(id)
                title.setText(R.string.edit_movie)
                btMovieSave.setText(R.string.Update)
            }
        }

        //Seta valor para os itens do layout
        etMovieName.setText(movieViewModel.movie.name)
        etMovieDescription.setText(movieViewModel.movie.description)
        etMovieCategory.setText(movieViewModel.movie.category)
        etMovieDisplayPlataform.setText(movieViewModel.movie.displayPlatform)
        rbMovieScore.rating =  movieViewModel.movie.score
        cvMovieDisplayDate.setDate(movieViewModel.movie.displayDate.time,true,true)

        //Ação de persistencia
        btMovieSave.setOnClickListener() {
            try {

                //Valida campos obrigatórios, via extension
                etMovieName.validate(activity)
                etMovieCategory.validate(activity)
                etMovieDisplayPlataform.validate(activity)

                val movie = movieViewModel.movie
                movie.name = etMovieName.text.toString()
                movie.description = etMovieDescription.text.toString()
                movie.category = etMovieCategory.text.toString()
                movie.displayPlatform = etMovieDisplayPlataform.text.toString()
                movie.displayDate= Date(cvMovieDisplayDate.date)
                movie.score = rbMovieScore.rating
                movieViewModel.saveChanges()

                //Avisa usuário
                Toast.makeText(getActivity(), R.string.movie_saved, Toast.LENGTH_LONG).show()

                //Redireciona para lista de movies
                val f: Fragment = AllMoviesFragment()
                val args = Bundle()
                args.putLong(AllMoviesFragment.EXTRA_MOVIEID, movie.id)
                f.arguments = args

                //Troca de fragmento
                val ft = fragmentManager?.beginTransaction()
                ft?.replace(R.id.nav_host_fragment, f)
                ft?.addToBackStack(null)
                ft?.commit()

            } catch (ex: Exception) {
                Toast.makeText(getActivity(),ex.message, Toast.LENGTH_LONG).show()
            }
        }

        return root

    }

    companion object {
        const val EXTRA_MOVIEID = "br.com.fiap.androidfinalprojeto.newmoviewfragment.EXTRA_MOVIEID"
    }

}