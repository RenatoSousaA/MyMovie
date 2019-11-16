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
import br.com.fiap.androidfinalprojeto.view.login.LoginActivity

class ExitFragment : Fragment() {

    //private lateinit var exitViewModel: ExitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signOut()
    }

    private fun signOut() {

        //Limpa as preferencias do usuario
        val p = PreferenceManager.getDefaultSharedPreferences(activity)
        val editor = p.edit()
        editor.clear()
        editor.commit()

        //Desloga firebase
        FirebaseAuth.getInstance().signOut()

        //Redireciona para o login
        val intent = Intent(activity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)//Fecha todas as activities ativas e coloca esta no lugar
        startActivity(intent)

    }
}