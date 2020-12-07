package com.rejangtoindo.kamusbahasarejang.dao


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "kamus")
data class KamusEntity2(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val rejang: String,
    val indo: String,
    val kelas_kata: String
)