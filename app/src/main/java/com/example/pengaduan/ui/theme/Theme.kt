package com.example.pengaduan.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Biru,
    onPrimary = Color.White,
    primaryContainer = UnguPudar,
    onPrimaryContainer = Color.Black,
    secondary = Emas,
    onSecondary = Color.White,
    background = Color(0xFFFDFDFD),
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

private val DarkColorScheme = darkColorScheme(
    primary = Biru,
    onPrimary = Color.White,
    primaryContainer = UnguPudar,
    onPrimaryContainer = Color.Black,
    secondary = Emas,
    onSecondary = Color.White,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White
)

@Composable
fun PengaduanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
