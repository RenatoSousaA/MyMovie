package br.com.fiap.androidfinalprojeto.view.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.view.login.LoginActivity
import br.com.fiap.androidfinalprojeto.view.main.MainActivity
import br.com.fiap.androidfinalprojeto.view.signup.SignupActivity

class SplashActivity : AppCompatActivity() {

    private val timeSplash = 4000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        load()
    }

    private fun load() {

        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val isFirstOpen = preferences.getBoolean("open_first",true)
        if (isFirstOpen) {
            markAppAlreadyOpen(preferences)
            showSplash()
        } else {
            showLogin()
        }

    }

    private fun markAppAlreadyOpen(preferences: SharedPreferences) {
        val editor = preferences.edit()
        editor.putBoolean("open_first", false)
        editor.apply()
    }

    private fun showSplash() {
        Handler().postDelayed({ showLogin() }, timeSplash)
    }

    private fun showLogin() {
        val nextScreen = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(nextScreen)
        finish()
    }

}
