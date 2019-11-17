package br.com.fiap.androidfinalprojeto.di

import androidx.room.Room
import br.com.fiap.androidfinalprojeto.room.dao.MyMovieDatabase
import br.com.fiap.androidfinalprojeto.room.repository.MovieRepository
import br.com.fiap.androidfinalprojeto.view.main.ui.all_movie.AllMoviesListAdapter
import br.com.fiap.androidfinalprojeto.view.main.ui.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single {
        MovieRepository(get())
    }
}

val dbModule = module {

    single {
        Room.databaseBuilder (
            get(),
            MyMovieDatabase::class.java,
            "MyMovieDatabase"
        ).build()
    }

    single {
        get<MyMovieDatabase>().movieDao()
    }

}

val viewModelModule = module {
    viewModel { MovieViewModel (get(), get()) }
}

/*
val uiModule = module {
    factory { AllMoviesListAdapter(get()) }
}
*/