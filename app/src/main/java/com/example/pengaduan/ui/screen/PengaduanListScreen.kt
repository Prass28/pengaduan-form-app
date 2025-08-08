package com.example.pengaduan.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pengaduan.data.model.Pengaduan
import com.example.pengaduan.ui.theme.Biru
import com.example.pengaduan.ui.theme.Emas
import com.example.pengaduan.ui.theme.UnguPudar
import com.example.pengaduan.ui.theme.orange
import com.example.pengaduan.viewmodel.PengaduanViewModel

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun PengaduanListScreen(
    viewModel: PengaduanViewModel,
    navController: NavHostController

) {
    val pengaduanList by viewModel.pengaduanList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Daftar Pengaduan", color = Color.Black)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White // misalnya warna latar belakang biru
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("formPengaduan") // Untuk tambah data
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah")
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .background(orange)
                .padding(16.dp)
        ) {
            items(pengaduanList) { pengaduan ->
                PengaduanItem(
                    pengaduan = pengaduan,
                    onEdit = {
                        // Navigasi dengan data untuk diedit (bisa pakai route + args atau NavBackStackEntry + sharedViewModel)
                        // Di sini kita langsung kirim ke form dengan ID (pastikan routing mendukung)
                        navController.navigate("formPengaduan") {
                            launchSingleTop = true
                        }
                        navController.currentBackStackEntry?.savedStateHandle?.set("pengaduanToEdit", pengaduan)
                    },
                    onDelete = {
                        viewModel.delete(pengaduan)
                    },
                    onDetail = {
//                        navController.currentBackStackEntry?.savedStateHandle?.set("pengaduanDetail", pengaduan)
//                        navController.navigate("detailPengaduan")
                        navController.navigate("detailPengaduan") {
                            launchSingleTop = true
                        }
                        navController.currentBackStackEntry?.savedStateHandle?.set("pengaduanDetail", pengaduan)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun PengaduanItem(
    pengaduan: Pengaduan,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onDetail: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(UnguPudar),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Nama: ${pengaduan.nama}", style = MaterialTheme.typography.titleMedium)
            Text("Deskripsi: ${pengaduan.deskripsi}", style = MaterialTheme.typography.bodyMedium)
            Text("Status: ${pengaduan.status}", style = MaterialTheme.typography.bodyMedium)
            Text("Tanggal: ${pengaduan.tanggal}", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onEdit,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Emas,
                        contentColor = Color.White
                    )
                ) {
                    Text("Edit")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onDetail,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Emas,
                        contentColor = Color.White
                    )
                ) {
                    Text("Detail")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onDelete,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Emas,
                        contentColor = Color.White
                    )
                ) {
                    Text("Hapus")
                }
            }
        }
    }
}
