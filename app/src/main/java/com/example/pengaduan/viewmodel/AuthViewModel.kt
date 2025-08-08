package com.example.pengaduan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pengaduan.data.db.AppDatabase
import com.example.pengaduan.data.model.User
import com.example.pengaduan.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(app: Application) : AndroidViewModel(app) {

    private val repo: AuthRepository = AuthRepository(
        AppDatabase.getDatabase(app).userDao()
    )

    private val _loginState = MutableStateFlow<User?>(null)
    val loginState: StateFlow<User?> = _loginState

    /**
     * Mendaftarkan user baru jika username belum terdaftar.
     */
    fun register(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val existing = repo.findByUsername(username)
            if (existing == null) {
                repo.register(User(username = username, password = password))
                onResult(true)
            } else {
                onResult(false)
            }
        }
    }

    /**
     * Login user dan update loginState
     */
    fun login(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = repo.login(username, password)
            _loginState.value = user
            onResult(user != null)
        }
    }

}
