package com.example.pengaduan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pengaduan.data.model.Pengaduan
import com.example.pengaduan.ui.screen.DetailPengaduanScreen
import com.example.pengaduan.ui.screen.FormPengaduanScreen
import com.example.pengaduan.ui.screen.LoginScreen
import com.example.pengaduan.ui.screen.RegisterScreen
import com.example.pengaduan.ui.screen.PengaduanListScreen
import com.example.pengaduan.viewmodel.PengaduanViewModel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object PengaduanList : Screen("pengaduan_list")
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    pengaduanViewModel: PengaduanViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }

        composable(Screen.PengaduanList.route) {
            PengaduanListScreen(
                navController = navController,
                viewModel = pengaduanViewModel
            )
        }

        composable("formPengaduan") { navBackStackEntry ->
            val pengaduan = navBackStackEntry
                .savedStateHandle
                ?.get<Pengaduan>("pengaduanToEdit")

            FormPengaduanScreen(
                navController = navController,
                pengaduanViewModel = pengaduanViewModel,
                pengaduanToEdit = pengaduan
            )
        }


        composable("detailPengaduan") { backStackEntry ->
            val pengaduan = backStackEntry.savedStateHandle.get<Pengaduan>("pengaduanDetail")
            pengaduan?.let {
                DetailPengaduanScreen(
                    navController = navController,
                    pengaduan = pengaduan,
                    pengaduanViewModel = pengaduanViewModel
                )
            }
        }







    }
}
