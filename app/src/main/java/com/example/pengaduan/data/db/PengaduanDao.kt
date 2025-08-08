package com.example.pengaduan.data.db

import androidx.room.*
import com.example.pengaduan.data.model.Pengaduan
import kotlinx.coroutines.flow.Flow

@Dao
interface PengaduanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pengaduan: Pengaduan)

    @Update
    suspend fun update(pengaduan: Pengaduan)

    @Delete
    suspend fun delete(pengaduan: Pengaduan)

    @Query("SELECT * FROM pengaduan ORDER BY id DESC")
    fun getAll(): Flow<List<Pengaduan>>

    @Query("SELECT * FROM pengaduan WHERE id = :id")
    suspend fun getById(id: Int): Pengaduan?
}
