package br.com.fiap.androidfinalprojeto.view.main.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.view.scan.ScanActivity
import br.com.fiap.androidfinalprojeto.view.splash.SplashActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onResume() {
        super.onResume()
        bt_code.setOnClickListener {
            val intent = Intent (activity, ScanActivity::class.java)
            startActivity(intent)
        }
    }
}