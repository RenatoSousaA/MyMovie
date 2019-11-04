package br.com.fiap.androidfinalprojeto.view.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.fiap.androidfinalprojeto.model.User
import br.com.fiap.androidfinalprojeto.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application, private val userRepository: UserRepository) : AndroidViewModel(application) {

    val allUsers: LiveData<List<User>> = userRepository.allUsers

    fun insert(user: User, setPassword: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.insert(user, setPassword)
    }

    fun delete(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.delete(user)
    }

}