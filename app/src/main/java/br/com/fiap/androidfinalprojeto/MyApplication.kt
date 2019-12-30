package br.com.fiap.androidfinalprojeto

import android.app.Application
import br.com.fiap.androidfinalprojeto.di.dbModule
import br.com.fiap.androidfinalprojeto.di.repositoryModule
import br.com.fiap.androidfinalprojeto.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

//Não esquecer de setar no androidManifest
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(
                viewModelModule,
                repositoryModule,
                dbModule
            ))
        }

    }
}