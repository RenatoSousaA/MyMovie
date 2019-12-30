package br.com.fiap.androidfinalprojeto.view.main.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.fiap.androidfinalprojeto.R
import kotlinx.android.synthetic.main.fragment_about.*
import br.com.fiap.utilities.*

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

        return root
    }

    override fun onResume() {
        super.onResume()
        bt_phone_call.setOnClickListener {
            val intent = PhoneCall().dialNumber("01133858010")
            startActivity(intent)
        }
    }
}