package br.com.fiap.androidfinalprojeto.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.fiap.androidfinalprojeto.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Delete()
    suspend fun delete(movie: Movie)

    @Query("DELETE FROM MVE01")
    fun deleteAll()

    @Query("SELECT * from MVE01 ORDER BY displayDate DESC, name ASC")
    fun getAll(): LiveData<List<Movie>>

}