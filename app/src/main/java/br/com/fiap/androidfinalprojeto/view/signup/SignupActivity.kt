package br.com.fiap.androidfinalprojeto.view.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.firebase.model.User
import br.com.fiap.androidfinalprojeto.view.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()

        btCreate.setOnClickListener {

            mAuth.createUserWithEmailAndPassword(
                inputEmail.text.toString(),
                inputPassword.text.toString()
            ).addOnCompleteListener {
                if(it.isSuccessful) {
                    saveInRealtimeDatabase()
                } else {
                    Toast.makeText(this@SignupActivity, it.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun saveInRealtimeDatabase() {

        val user = User (
            inputName.text.toString(),
            inputEmail.text.toString(),
            inputPhone.text.toString()
        )

        if(mAuth.currentUser == null) {
            FirebaseDatabase
                .getInstance()
                .getReference("Users").child(mAuth.currentUser!!.uid)
                .setValue(user)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        val resultIntent = Intent()
                        resultIntent.putExtra("email", inputEmail.text.toString())
                        setResult(Activity.RESULT_OK, resultIntent)
                        goToHome()
                    } else {
                        Toast.makeText(this@SignupActivity, it.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }
        }

    }

    private fun goToHome() {

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)//Fecha todas as activities ativas e coloca esta no lugar
        startActivity(intent)
        finish()
    }

}
