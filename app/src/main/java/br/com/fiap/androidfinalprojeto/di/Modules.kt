package br.com.fiap.androidfinalprojeto.di

import androidx.room.Room
import br.com.fiap.androidfinalprojeto.dao.MyMovieDatabase
import br.com.fiap.androidfinalprojeto.repository.UserRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single {
        UserRepository(get())
    }
}

val dbModule = module {
    single {
        Room.databaseBuilder (
            get(),
            MyMovieDatabase::class.java,"MyMovieDatabase"
        ).build()
    }

    single {
        get<MyMovieDatabase>().userDao()
    }
}

/*
val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}

val uiModule = module {
    factory { WordListAdapter(get()) }
}
*/