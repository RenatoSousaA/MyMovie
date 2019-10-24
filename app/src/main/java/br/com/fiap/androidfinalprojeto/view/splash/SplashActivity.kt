package br.com.fiap.androidfinalprojeto.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.view.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private val timeSplash = 4000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        load()
    }

    private fun load() {
        Handler().postDelayed({
            val startProgram = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(startProgram)
            finish()
        }, timeSplash)
    }
}
