package br.com.fiap.androidfinalprojeto.view.login

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.view.main.MainActivity
import br.com.fiap.androidfinalprojeto.view.signup.SignupActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    val permissoes: List<String> = listOf(android.Manifest.permission.ACCESS_FINE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        if (user != null) {
            goToHome()
        }


        validarPermissoes(permissoes, this, 1)

        btLogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                inputLoginEmail.text.toString(),
                inputLoginPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    goToHome()
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        btSignup.setOnClickListener {
            startActivityForResult(Intent(this, SignupActivity::class.java), 100)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            inputLoginEmail.setText(data?.getStringExtra("email"))
        }
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)//Fecha todas as activities ativas e coloca esta no lugar
        startActivity(intent)
        finish()
    }

    private fun validarPermissoes(permissoes: List<String>, activity: Activity, requestCode: Int): Boolean {
        val listaPermissoes = ArrayList<String>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permissao in permissoes) {
                val temPermissao =
                    ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED
                if (!temPermissao) listaPermissoes.add(permissao)
            }

            if (listaPermissoes.isEmpty()) return true
            else {
                val novasPermissoes = arrayOfNulls<String>(listaPermissoes.size)
                listaPermissoes.toTypedArray()
                ActivityCompat.requestPermissions(activity, listaPermissoes.toTypedArray(), requestCode)
            }
        }
        return true
    }

}
