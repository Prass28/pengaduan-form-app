package com.example.pengaduan.data.repository

import com.example.pengaduan.data.db.UserDao
import com.example.pengaduan.data.model.User

class AuthRepository(private val userDao: UserDao) {

    // Registrasi pengguna baru
    suspend fun register(user: User): Long = userDao.register(user)

    // Login pengguna berdasarkan username dan password
    suspend fun login(username: String, password: String): User? = userDao.login(username, password)

    // Cek apakah username sudah terdaftar
    suspend fun findByUsername(username: String): User? = userDao.findByUsername(username)

}
