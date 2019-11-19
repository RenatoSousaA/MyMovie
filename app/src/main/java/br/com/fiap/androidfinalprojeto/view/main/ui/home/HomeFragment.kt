package br.com.fiap.androidfinalprojeto.view.main.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.view.main.MainActivity
import br.com.fiap.androidfinalprojeto.view.main.ui.all_movie.AllMoviesFragment
import br.com.fiap.androidfinalprojeto.view.main.ui.movie.MovieFragment
import br.com.fiap.androidfinalprojeto.view.main.ui.movie.MovieViewModel
import br.com.fiap.androidfinalprojeto.view.scan.ScanActivity
import br.com.fiap.androidfinalprojeto.view.splash.SplashActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val btSearch = root.findViewById<Button>(R.id.bt_search)
        val etMovieName = root.findViewById<EditText>(R.id.et_name)

        val nameMovie = MainActivity.EXTRA_MOVIEID
        MainActivity.EXTRA_MOVIEID = ""

        if (nameMovie != "") {
            val movieName = nameMovie

            //Redireciona para lista de movies
            val f: Fragment = AllMoviesFragment()
            val args = Bundle()
            args.putString(AllMoviesFragment.EXTRA_MOVIEID, movieName)
            f.arguments = args

            //Troca de fragmento
            val ft = fragmentManager?.beginTransaction()
            ft?.replace(R.id.nav_host_fragment, f)
            ft?.addToBackStack(null)
            ft?.commit()
        }

        btSearch.setOnClickListener {
            try {

                val movieName = etMovieName.text.toString()

                //Redireciona para lista de movies
                val f: Fragment = AllMoviesFragment()
                val args = Bundle()
                args.putString(AllMoviesFragment.EXTRA_MOVIEID, movieName)
                f.arguments = args

                //Troca de fragmento
                val ft = fragmentManager?.beginTransaction()
                ft?.replace(R.id.nav_host_fragment, f)
                ft?.addToBackStack(null)
                ft?.commit()

            } catch (ex: Exception) {
                Toast.makeText(activity, ex.message, Toast.LENGTH_LONG).show()
            }
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        bt_code.setOnClickListener {
            val intent = Intent (activity, ScanActivity::class.java)

            val ft = fragmentManager?.beginTransaction()
            ft?.addToBackStack(null)
            ft?.commit()

            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_MOVIEID = "br.com.fiap.androidfinalprojeto.newmoviewfragment.EXTRA_MOVIEID"
    }
}