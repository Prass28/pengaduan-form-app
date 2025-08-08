package com.example.pengaduan.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pengaduan.data.model.Pengaduan
import com.example.pengaduan.ui.theme.Biru
import com.example.pengaduan.ui.theme.Emas
import com.example.pengaduan.ui.theme.UnguPudar
import com.example.pengaduan.ui.theme.orange
import com.example.pengaduan.viewmodel.PengaduanViewModel
import okhttp3.internal.wait
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPengaduanScreen(
    navController: NavController,
    pengaduanViewModel: PengaduanViewModel,
    pengaduanToEdit: Pengaduan? = null,
) {
    val context = LocalContext.current

    var nama by remember { mutableStateOf(TextFieldValue("")) }
    var deskripsi by remember { mutableStateOf(TextFieldValue("")) }
    var status by remember { mutableStateOf("Menunggu") }
    var tipe by remember { mutableStateOf("Layanan") }
    var fotoUri by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(pengaduanToEdit) {
        pengaduanToEdit?.let {
            nama = TextFieldValue(it.nama)
            deskripsi = TextFieldValue(it.deskripsi)
            status = it.status
            tipe = it.tipe
            fotoUri = TextFieldValue(it.fotoUri ?: "")
        }
    }

    val isEditing = pengaduanToEdit != null
    var expandedStatus by remember { mutableStateOf(false) }
    var expandedTipe by remember { mutableStateOf(false) }

    val statusOptions = listOf("Menunggu", "Diproses", "Selesai")
    val tipeOptions = listOf("Layanan", "Keamanan", "Fasilitas")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(orange)
            .padding(16.dp)
    ) {
        Text(
            text = if (isEditing) "Edit Pengaduan" else "Tambah Pengaduan",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        )

        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text("Nama", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Emas,
                unfocusedBorderColor = Emas,
                focusedLabelColor = Emas,
                unfocusedLabelColor = Color.White,
                cursorColor = Emas,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = deskripsi,
            onValueChange = { deskripsi = it },
            label = { Text("Deskripsi", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Emas,
                unfocusedBorderColor = Emas,
                focusedLabelColor = Emas,
                unfocusedLabelColor = Color.White,
                cursorColor = Emas,
                focusedTextColor = Color.White,
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Status Dropdown
        ExposedDropdownMenuBox(
            expanded = expandedStatus,
            onExpandedChange = { expandedStatus = !expandedStatus }
        ) {
            OutlinedTextField(
                value = status,
                onValueChange = {},
                readOnly = true,
                label = { Text("Status", color = Color.White) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStatus) },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Emas,
                    unfocusedBorderColor = Emas,
                    focusedLabelColor = Emas,
                    unfocusedLabelColor = Color.White,
                    cursorColor = Emas,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                )
            )

            ExposedDropdownMenu(
                expanded = expandedStatus,
                onDismissRequest = { expandedStatus = false },
                modifier = Modifier.background(UnguPudar)
            ) {
                statusOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, color = Color.White) },
                        onClick = {
                            status = option
                            expandedStatus = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Tipe Dropdown
        ExposedDropdownMenuBox(
            expanded = expandedTipe,
            onExpandedChange = { expandedTipe = !expandedTipe }
        ) {
            OutlinedTextField(
                value = tipe,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipe Pengaduan", color = Color.White) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTipe) },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Emas,
                    unfocusedBorderColor = Emas,
                    focusedLabelColor = Emas,
                    unfocusedLabelColor = Color.White,
                    cursorColor = Emas,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                )
            )

            ExposedDropdownMenu(
                expanded = expandedTipe,
                onDismissRequest = { expandedTipe = false },
                modifier = Modifier.background(UnguPudar)
            ) {
                tipeOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, color = Color.White) },
                        onClick = {
                            tipe = option
                            expandedTipe = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = fotoUri,
            onValueChange = { fotoUri = it },
            label = { Text("URL Foto (opsional)", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Emas,
                unfocusedBorderColor = Emas,
                focusedLabelColor = Emas,
                unfocusedLabelColor = Color.White,
                cursorColor = Emas,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                val pengaduan = Pengaduan(
                    id = pengaduanToEdit?.id ?: 0,
                    nama = nama.text,
                    deskripsi = deskripsi.text,
                    status = status,
                    tanggal = pengaduanToEdit?.tanggal ?: currentDate,
                    tipe = tipe,
                    fotoUri = fotoUri.text.ifBlank { null }
                )

                if (isEditing) {
                    pengaduanViewModel.update(pengaduan)
                    Toast.makeText(context, "Pengaduan berhasil diperbarui", Toast.LENGTH_SHORT).show()
                } else {
                    pengaduanViewModel.insert(pengaduan)
                    Toast.makeText(context, "Pengaduan berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                }

                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Emas, contentColor = Color.White)
        ) {
            Text(if (isEditing) "Perbarui" else "Simpan")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
        ) {
            Text("Batal", color = Color.White)
        }

        if (isEditing) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = {
                    pengaduanToEdit?.let {
                        pengaduanViewModel.delete(it)
                        Toast.makeText(context, "Pengaduan dihapus", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Hapus")
            }
        }
    }
}
