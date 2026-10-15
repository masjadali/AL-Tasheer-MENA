package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GoldLight,
    onPrimary = NavyPrimary,
    primaryContainer = NavyVariant,
    onPrimaryContainer = GoldLight,
    secondary = GoldPrimary,
    onSecondary = Color.Black,
    secondaryContainer = NavyLightSurface,
    onSecondaryContainer = GoldLight,
    tertiary = WhatsAppGreen,
    onTertiary = Color.Black,
    background = BackgroundDark,
    onBackground = TextDarkPrimary,
    surface = SurfaceDark,
    onSurface = TextDarkPrimary,
    surfaceVariant = SurfaceElevatedDark,
    onSurfaceVariant = TextDarkSecondary,
    outline = BorderDark,
    outlineVariant = NavyLightSurface
)

private val LightColorScheme = lightColorScheme(
    primary = NavyPrimary,
    onPrimary = Color.White,
    primaryContainer = NavyVariant,
    onPrimaryContainer = Color.White,
    secondary = GoldDark,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFBF6EC),
    onSecondaryContainer = GoldDark,
    tertiary = WhatsAppDarkGreen,
    onTertiary = Color.White,
    background = BackgroundLight,
    onBackground = SlateDark,
    surface = SurfaceLight,
    onSurface = SlateDark,
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = SlateMedium,
    outline = SlateLightBorder,
    outlineVariant = Color(0xFFE2E8F0)
)

@Composable
fun TasheerMenaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep consistent corporate branding by default
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
