package br.com.fiap.androidfinalprojeto.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Adotar padrão de 5 caracteres para as tabelas
//3 para prefixo e 2 para ordem entre pais e filhos
@Entity(tableName = "USR01")
class User(
    @PrimaryKey val email: String,
    val fullName: String,
    val phone: String,
    var password: String
)