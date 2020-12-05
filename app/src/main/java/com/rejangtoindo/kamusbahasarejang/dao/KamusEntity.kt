package com.rejangtoindo.kamusbahasarejang.dao


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "kamus")
data class KamusEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val indo: String,
    val rejang: String,
    val kelas_kata: String
)