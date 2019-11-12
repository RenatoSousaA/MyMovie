package br.com.fiap.androidfinalprojeto.view.main.ui.newMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewMovieViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "New movie"
    }
    val text: LiveData<String> = _text
}