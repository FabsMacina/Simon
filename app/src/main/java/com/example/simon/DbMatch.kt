package com.example.simon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbMatch(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "count")
    val count: Int?,

    @ColumnInfo(name = "sequence")
    val sequence: String?
)