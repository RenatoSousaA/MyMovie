package br.com.fiap.androidfinalprojeto.view.main

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.fiap.androidfinalprojeto.R
import com.google.firebase.database.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.android.synthetic.main.all_movies_recyclerview.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var remoteConfig: FirebaseRemoteConfig
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        navView.checkedItem

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_new_movie, R.id.nav_maps,
                R.id.nav_all_movies, R.id.nav_about, R.id.nav_sair
            ), drawerLayout
        )

        remoteConfig = FirebaseRemoteConfig.getInstance()

        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(60)
            .build()

        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.setDefaultsAsync(R.xml.remote_config_defauts)

        attRemoteConfig()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun attRemoteConfig() {
        val navView: NavigationView = findViewById(R.id.nav_view)
        val headerView = navView.getHeaderView(0)
        val navTitle = headerView.findViewById(R.id.title_navigationView) as TextView
        val navSubTitle = headerView.findViewById(R.id.subtitle_navigationView) as TextView

        var subtitleNavigation = remoteConfig.getString("subtitle_navigation")
        var titleNavigation = remoteConfig.getString("title_navigation")



        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) {
                subtitleNavigation = remoteConfig.getString("subtitle_navigation")
                titleNavigation = remoteConfig.getString("title_navigation")

                navTitle.text = titleNavigation
                navSubTitle.text = subtitleNavigation
            }
    }

}