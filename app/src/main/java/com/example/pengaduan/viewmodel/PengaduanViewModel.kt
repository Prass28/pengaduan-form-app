package com.example.pengaduan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pengaduan.data.db.AppDatabase
import com.example.pengaduan.data.repository.PengaduanRepository
import com.example.pengaduan.data.model.Pengaduan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PengaduanViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PengaduanRepository =
        PengaduanRepository(AppDatabase.getDatabase(application).pengaduanDao())

    val pengaduanList: StateFlow<List<Pengaduan>> =
        repository.allPengaduan
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun insert(pengaduan: Pengaduan) = viewModelScope.launch {
        repository.insert(pengaduan)
    }

    fun update(pengaduan: Pengaduan) = viewModelScope.launch {
        repository.update(pengaduan)
    }

    fun delete(pengaduan: Pengaduan) = viewModelScope.launch {
        repository.delete(pengaduan)
    }
}
