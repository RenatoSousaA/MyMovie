package br.com.fiap.androidfinalprojeto.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import br.com.fiap.androidfinalprojeto.dao.UserDao
import br.com.fiap.androidfinalprojeto.model.User
import org.apache.commons.codec.digest.DigestUtils
import android.R.attr.data



class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAll()

    @WorkerThread//Anotação para rodar em background
    suspend fun insert(user: User, setPassword: Boolean) {
        if (setPassword) {
            user.password = DigestUtils.md5Hex(user.password)
        }
        userDao.insert(user)
    }

    @WorkerThread//Anotação para rodar em background
    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    //Rodar de forma sincrona
    fun getUser(email: String, password: String) {
        val passwordMD5 = DigestUtils.md5Hex(password)
        userDao.getUser(email, passwordMD5)
    }
}