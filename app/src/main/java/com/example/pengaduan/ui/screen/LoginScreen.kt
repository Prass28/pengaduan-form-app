package com.example.pengaduan.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pengaduan.ui.navigation.Screen
import com.example.pengaduan.ui.theme.Biru
import com.example.pengaduan.ui.theme.Emas
import com.example.pengaduan.ui.theme.UnguPudar
import com.example.pengaduan.ui.theme.orange
import com.example.pengaduan.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val userState by authViewModel.loginState.collectAsState()

    // Jika user berhasil login, navigasi ke list
    LaunchedEffect(userState) {
        if (userState != null) {
            navController.navigate(Screen.PengaduanList.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(orange)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Emas,
                unfocusedBorderColor = Emas,
                textColor = Color.White,
                backgroundColor = Color.White
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Emas,
                unfocusedBorderColor = Emas,
                textColor = Color.White,
                backgroundColor = Color.White
            )

        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (username.isNotBlank() && password.isNotBlank()) {
                    authViewModel.login(username, password) { success ->
                        if (success) {
                            navController.navigate(Screen.PengaduanList.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        } else {
                            errorMessage = "Username atau password salah"
                        }
                    }
                } else {
                    errorMessage = "Username dan password wajib diisi"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Emas)
        ) {
            Text("Login", color = Color.White)
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = MaterialTheme.colors.error)
        }

        TextButton(
            onClick = {
                navController.navigate(Screen.Register.route)
            }
        ) {
            Text("Belum punya akun? Register", color = Color.White)
        }
    }
}
