package com.example.pengaduan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.pengaduan.ui.navigation.NavigationGraph
import com.example.pengaduan.ui.theme.PengaduanTheme
import com.example.pengaduan.viewmodel.PengaduanViewModel
import com.example.pengaduan.viewmodel.PengaduanViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pengaduanViewModel = ViewModelProvider(
            this,
            PengaduanViewModelFactory(application)
        )[PengaduanViewModel::class.java]

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            PengaduanTheme {
                NavigationGraph(
                    navController = navController,
                    pengaduanViewModel = pengaduanViewModel
                )
            }
        }
    }
}
