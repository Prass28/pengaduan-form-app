package com.example.pengaduan.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.pengaduan.data.model.Pengaduan
import com.example.pengaduan.ui.theme.Biru
import com.example.pengaduan.ui.theme.orange
import com.example.pengaduan.viewmodel.PengaduanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPengaduanScreen(
    navController: NavController,
    pengaduan: Pengaduan,
    pengaduanViewModel: PengaduanViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Pengaduan", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            Text("Nama", fontWeight = FontWeight.Bold, color = Color.DarkGray)
            Text(pengaduan.nama, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Deskripsi", fontWeight = FontWeight.Bold, color = Color.DarkGray)
            Text(pengaduan.deskripsi, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Status", fontWeight = FontWeight.Bold, color = Color.DarkGray)
            Text(pengaduan.status, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Tanggal", fontWeight = FontWeight.Bold, color = Color.DarkGray)
            Text(pengaduan.tanggal, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Tipe", fontWeight = FontWeight.Bold, color = Color.DarkGray)
            Text(pengaduan.tipe, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Tampilkan gambar jika ada URI
            pengaduan.fotoUri?.takeIf { it.isNotBlank() }?.let { fotoUri ->
                Text("Foto", fontWeight = FontWeight.Bold, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(fotoUri)
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = "Foto Pengaduan",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Kembali")
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set("pengaduanToEdit", pengaduan)
                        navController.navigate("formPengaduan")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Edit")
                }
            }
        }
    }
}
