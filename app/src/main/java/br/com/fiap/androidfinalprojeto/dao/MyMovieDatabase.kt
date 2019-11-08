package br.com.fiap.androidfinalprojeto.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fiap.androidfinalprojeto.model.Movie
import br.com.fiap.androidfinalprojeto.util.Converters

@Database(entities = [Movie::class], version = 1)
@TypeConverters(Converters::class)//Necessário para utilização do tipo complexo date nos models
public abstract class MyMovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MyMovieDatabase? = null

        fun getDatabase(context: Context): MyMovieDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyMovieDatabase::class.java,
                    "MyMovieDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}