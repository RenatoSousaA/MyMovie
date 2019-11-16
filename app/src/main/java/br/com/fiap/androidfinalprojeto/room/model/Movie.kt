package br.com.fiap.androidfinalprojeto.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//Adotar padrão de 5 caracteres para as tabelas
//3 para prefixo e 2 para ordem entre pais e filhos
@Entity(tableName = "MVE01")
class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String = "",
    var displayDate: Date = Calendar.getInstance().getTime(), // Aceito somente com a implementação da classe Converters e a decoração TypeConverters em MyMovieDatabase
    var description: String = "",
    var category: String = "",
    var displayPlatform: String = "",
    var score: Float = 0.toFloat()
)