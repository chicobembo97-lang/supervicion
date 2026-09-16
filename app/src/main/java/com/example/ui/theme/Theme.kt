package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
  primary = BrandBlueInteractive,
  onPrimary = Color.White,
  primaryContainer = BrandBlueLight,
  onPrimaryContainer = BrandBlueDark,
  secondary = DeepNavy,
  onSecondary = Color.White,
  secondaryContainer = SurfaceSubtle,
  onSecondaryContainer = DeepNavy,
  tertiary = EmeraldTarget,
  onTertiary = Color.White,
  tertiaryContainer = EmeraldLight,
  onTertiaryContainer = EmeraldDark,
  background = SurfaceCanvas,
  onBackground = TextPrimary,
  surface = SurfaceCard,
  onSurface = TextPrimary,
  surfaceVariant = SurfaceSubtle,
  onSurfaceVariant = TextSecondary,
  outline = SurfaceBorder,
  outlineVariant = OutlineVariant,
  error = RedDeficit,
  onError = Color.White,
  errorContainer = RedLight,
  onErrorContainer = RedDeficit,
)

private val DarkColorScheme = darkColorScheme(
  primary = BrandBlueInteractive,
  onPrimary = Color.White,
  background = BrandNavy,
  onBackground = Color.White,
  surface = DeepNavy,
  onSurface = Color.White,
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Preserve enterprise identity
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

