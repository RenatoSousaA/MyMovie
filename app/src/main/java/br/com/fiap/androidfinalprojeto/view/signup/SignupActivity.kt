package br.com.fiap.androidfinalprojeto.view.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.view.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*
import br.com.fiap.androidfinalprojeto.util.EditTextUtil.Companion.validate
import br.com.fiap.androidfinalprojeto.util.ResourceStringUtil.Companion.enforceGetString
import java.lang.Exception
import com.google.firebase.firestore.FirebaseFirestore
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.DocumentReference
import com.google.android.gms.tasks.OnSuccessListener
import java.util.*
import kotlin.collections.HashMap


class SignupActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        btCreate.setOnClickListener {
            try {

                validateRequiredFields()

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

            } catch (ex: Exception) {
                Toast.makeText(this@SignupActivity, ex.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun saveInRealtimeDatabase() {
        try {

            if (mAuth.currentUser != null) {

                val user = HashMap<String, Any>()
                user.put("authId", mAuth.currentUser!!.uid)
                user.put("name", inputName.text.toString())
                user.put("email",  inputEmail.text.toString())
                user.put("phone", inputPhone.text.toString())

                db.collection("Users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this@SignupActivity,
                            br.com.fiap.androidfinalprojeto.R.string.signup_success, Toast.LENGTH_LONG).show()
                        goToHome()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this@SignupActivity, e.message, Toast.LENGTH_LONG).show()
                    }

            }

        } catch (ex: Exception) {
            Toast.makeText(this@SignupActivity, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    private  fun validateRequiredFields() {
        inputName.validate(this@SignupActivity)
        inputEmail.validate(this@SignupActivity)
        inputPhone.validate(this@SignupActivity)
        inputPassword.validate(this@SignupActivity)
        inputConfirPassword.validate(this@SignupActivity)
        if(!inputPassword.text.toString().equals(inputConfirPassword.text.toString())) {
            throw Exception(R.string.passwords_do_not_match.enforceGetString(this@SignupActivity))
        }
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)//Fecha todas as activities ativas e coloca esta no lugar
        startActivity(intent)
        finish()
    }

}
