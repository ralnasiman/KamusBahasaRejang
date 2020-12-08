package com.rejangtoindo.kamusbahasarejang.dao


import androidx.room.*


@Dao
interface KamusDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKamus(kamus: KamusEntity)

    @Update
    fun updateKamus(kamus: KamusEntity)

    @Delete
    fun deleteKamus(kamus: KamusEntity)

    @Query("SELECT * FROM kamus WHERE indo == :name")
    fun cariIndo(name: String): List<KamusEntity>

    @Query("SELECT * FROM kamus WHERE rejang == :name")
    fun cariRejang(name: String): List<KamusEntity>
    @Query("SELECT * FROM kamus WHERE kelas_kata == :name")
    fun carikelaskata(name: String): List<KamusEntity>

    @Query("SELECT * FROM kamus ORDER BY indo DESC")
    fun getKamus(): List<KamusEntity>
    @Query("SELECT * FROM kamus order by rejang ASC")
    fun getKamusRejang(): List<KamusEntity2>
}