package br.com.fiap.androidfinalprojeto.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

//Adotar padrão de 5 caracteres para as tabelas
//3 para prefixo e 2 para ordem entre pais e filhos
@Entity(tableName = "MVE01")
class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val displayDate: Date?, // Aceito somente com a implementação da classe Converters e a decoração TypeConverters em MyMovieDatabase
    val description: String?,
    val category: String?,
    val displayPlatform: String?,
    val score: Float?
)