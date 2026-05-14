package com.example.simon

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DbMatch::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun dbmatchDao(): DbMatchDao
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(context, AppDatabase::class.java, "match_db")
                    .build().also { INSTANCE = it }
            }
        }
    }
}