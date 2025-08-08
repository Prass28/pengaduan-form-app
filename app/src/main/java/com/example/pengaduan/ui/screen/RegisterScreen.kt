package com.example.pengaduan.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import okhttp3.internal.wait

@Composable
fun RegisterScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(orange)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Register", fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
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
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = androidx.compose.ui.graphics.Color.Red)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                authViewModel.register(username, password) { success ->
                    if (success) {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    } else {
                        errorMessage = "Username sudah terdaftar"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Emas)
        ) {
            Text("Register", color = Color.White)
        }

        TextButton(onClick = {
            navController.navigate(Screen.Login.route)
        }) {
            Text("Sudah punya akun? Login", color = Color.White)
        }
    }
}

