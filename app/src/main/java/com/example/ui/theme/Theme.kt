package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
  primary = PrimaryAccent,
  onPrimary = TextPrimary,
  primaryContainer = CardBackgroundElevated,
  onPrimaryContainer = SecondaryAccent,
  secondary = SecondaryAccent,
  onSecondary = MainBackground,
  secondaryContainer = CardBackground,
  onSecondaryContainer = TextPrimary,
  tertiary = SecondaryAccent,
  background = MainBackground,
  onBackground = TextPrimary,
  surface = CardBackground,
  onSurface = TextPrimary,
  surfaceVariant = SecondaryBackground,
  onSurfaceVariant = TextMuted,
  outline = BorderDefault,
  outlineVariant = BorderHighlight,
  error = ErrorRed,
  onError = TextPrimary
)

@Composable
fun MyApplicationTheme(
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colorScheme = DarkColorScheme,
    typography = PortfolioTypography,
    content = content
  )
}
