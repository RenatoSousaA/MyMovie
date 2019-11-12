package br.com.fiap.androidfinalprojeto.view.main.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.fiap.androidfinalprojeto.R

class AboutFragment : Fragment() {
    private lateinit var aboutViewModel: AboutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
            ViewModelProviders.of(this).get(AboutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_about, container, false)
//        val textView: TextView = root.findViewById(R.id.text_about)
//        aboutViewModel.text.observe(this, Observer {
//            textView.text = it
//        })

        return root
    }
}