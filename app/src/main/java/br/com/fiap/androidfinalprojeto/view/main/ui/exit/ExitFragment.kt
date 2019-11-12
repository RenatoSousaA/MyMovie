package br.com.fiap.androidfinalprojeto.view.main.ui.exit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.fiap.androidfinalprojeto.R

class ExitFragment : Fragment() {

    private lateinit var exitViewModel: ExitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finish()
    }

    private fun finish() {
        System.exit(0)
    }
}