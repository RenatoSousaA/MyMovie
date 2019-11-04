package br.com.fiap.androidfinalprojeto.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.androidfinalprojeto.model.User

@Database(entities = [User::class], version = 1)
public abstract class MyMovieDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

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