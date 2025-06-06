package no.advokat.identer.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    // Primary colors
    primary = Primary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,

    // Secondary colors
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = LightOnSecondaryContainer,

    // Tertiary colors (using secondary as fallback)
    tertiary = LightSecondary,
    onTertiary = LightOnSecondary,
    tertiaryContainer = LightSecondaryContainer,
    onTertiaryContainer = LightOnSecondaryContainer,

    // Background and surface colors
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,
    surfaceVariant = LightBackground.copy(alpha = 0.7f),
    onSurfaceVariant = LightOnBackground,

    // Status colors
    error = LightError,
    onError = LightOnError,
    errorContainer = LightError.copy(alpha = 0.1f),
    onErrorContainer = LightError,

    // Other required Material 3 colors
    outline = LightOnBackground.copy(alpha = 0.5f),
    outlineVariant = LightOnBackground.copy(alpha = 0.2f),
    scrim = LightOnBackground.copy(alpha = 0.3f)
)

private val DarkColorScheme = darkColorScheme(
    // Primary colors
    primary = Primary, // Kept same as light theme
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,

    // Secondary colors
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = DarkOnSecondaryContainer,

    // Tertiary colors (using secondary as fallback)
    tertiary = DarkSecondary,
    onTertiary = DarkOnSecondary,
    tertiaryContainer = DarkSecondaryContainer,
    onTertiaryContainer = DarkOnSecondaryContainer,

    // Background and surface colors
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkBackground.copy(alpha = 0.7f),
    onSurfaceVariant = DarkOnBackground,

    // Status colors
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkError.copy(alpha = 0.1f),
    onErrorContainer = DarkError,

    // Other required Material 3 colors
    outline = DarkOnBackground.copy(alpha = 0.5f),
    outlineVariant = DarkOnBackground.copy(alpha = 0.2f),
    scrim = DarkOnBackground.copy(alpha = 0.3f)
)

@Composable
fun ForsvarerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}