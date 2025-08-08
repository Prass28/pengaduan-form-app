package com.example.pengaduan.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PengaduanViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PengaduanViewModel::class.java)) {
            return PengaduanViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
