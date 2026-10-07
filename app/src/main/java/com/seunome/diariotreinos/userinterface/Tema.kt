package com.seunome.diariotreinos.userinterface

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CoresDiarioTreinos = lightColorScheme(
    primary = Color(0xFFFF6B00),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFE0CC),
    onPrimaryContainer = Color(0xFF4A1D00),
    secondary = Color(0xFF3E3E3E),
    onSecondary = Color.White,
    background = Color(0xFFF7F7F5),
    surface = Color.White,
    surfaceVariant = Color(0xFFEFEDEA),
    onSurface = Color(0xFF171717),
    onBackground = Color(0xFF171717)
)

@Composable
fun DiarioTreinosTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = CoresDiarioTreinos, content = content)
}
