package br.com.fiap.androidfinalprojeto.view.main.ui.exit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import androidx.annotation.NonNull
import android.R
import br.com.fiap.androidfinalprojeto.view.main.MainActivity
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.method.TextKeyListener.clear

class ExitFragment : Fragment() {

    private lateinit var exitViewModel: ExitViewModel

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Finaliza somente depois do token destroido
        mAuth = FirebaseAuth.getInstance()
        mAuth.addAuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser == null) {
                finish()
            }
        }

        //Desloga e limpa preferencias
        signOut()

    }

    private fun finish() {
        System.exit(0)
    }

    private fun signOut() {

        //Limpa as preferencias do usuario
        val p = PreferenceManager.getDefaultSharedPreferences(activity)
        val editor = p.edit()
        editor.clear()
        editor.commit()

        //Desloga firebase
        mAuth.signOut()

    }
}