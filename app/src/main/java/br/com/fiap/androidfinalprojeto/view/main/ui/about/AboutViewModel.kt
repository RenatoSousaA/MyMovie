package br.com.fiap.androidfinalprojeto.view.main.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is send Fragment"
    }
    val text: LiveData<String> = _text
}