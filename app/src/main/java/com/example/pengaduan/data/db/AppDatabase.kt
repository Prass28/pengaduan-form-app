package com.example.pengaduan.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pengaduan.data.model.User
import com.example.pengaduan.data.model.Pengaduan

@Database(
    entities = [User::class, Pengaduan::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun pengaduanDao(): PengaduanDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pengaduan_db"
                )
                    .fallbackToDestructiveMigration() // Menghapus dan membuat ulang DB jika ada perubahan skema
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
