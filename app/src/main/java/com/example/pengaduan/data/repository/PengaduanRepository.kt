package com.example.pengaduan.data.repository

import com.example.pengaduan.data.db.PengaduanDao
import com.example.pengaduan.data.model.Pengaduan
import kotlinx.coroutines.flow.Flow

class PengaduanRepository(private val dao: PengaduanDao) {

    // Mengalirkan seluruh data pengaduan
    val allPengaduan: Flow<List<Pengaduan>> = dao.getAll()

    suspend fun insert(pengaduan: Pengaduan) {
        dao.insert(pengaduan)
    }

    suspend fun update(pengaduan: Pengaduan) {
        dao.update(pengaduan)
    }

    suspend fun delete(pengaduan: Pengaduan) {
        dao.delete(pengaduan)
    }

    suspend fun getById(id: Int): Pengaduan? {
        return dao.getById(id)
    }
}
