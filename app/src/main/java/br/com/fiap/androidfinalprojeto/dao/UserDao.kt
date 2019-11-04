package br.com.fiap.androidfinalprojeto.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.fiap.androidfinalprojeto.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Delete()
    suspend fun delete(user: User)

    @Query("DELETE FROM USR01")
    fun deleteAll()

    @Query("SELECT * from USR01 ORDER BY fullName ASC")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * from USR01 WHERE email=:email and password=:password ORDER BY fullName ASC")
    fun getUser(email: String, password: String): LiveData<User>

}