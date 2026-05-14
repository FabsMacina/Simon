package com.example.simon

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DbMatchDao{
    @Query("SELECT * FROM dbmatch")
    fun getAll(): List<DbMatch>
    @Insert
    fun insertAll(vararg matches: DbMatch)
    @Delete
    fun delete(dbmatch: DbMatch)
}