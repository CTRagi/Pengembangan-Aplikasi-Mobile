package com.mahawira.tugaspraktikum4.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private const val THEME_ANIMATION_MILLIS = 500

private val LightColors = lightColorScheme(
    primary = Color(0xFF3F51B5),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFDDE1FF),
    onPrimaryContainer = Color(0xFF0B1B5E),
    background = Color(0xFFF6F7FB),
    onBackground = Color(0xFF1B1B1F),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1B1B1F),
    surfaceVariant = Color(0xFFE3E4EC),
    onSurfaceVariant = Color(0xFF45464F),
    surfaceContainerHighest = Color(0xFFE3E4EC),
    outline = Color(0xFF767680),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFB9C3FF),
    onPrimary = Color(0xFF08218A),
    primaryContainer = Color(0xFF10153A),
    onPrimaryContainer = Color(0xFFDDE1FF),
    background = Color(0xFF0C0F26),
    onBackground = Color(0xFFE4E1E9),
    surface = Color(0xFF1C2044),
    onSurface = Color(0xFFE4E1E9),
    surfaceVariant = Color(0xFF2B2F55),
    onSurfaceVariant = Color(0xFFC6C5D0),
    surfaceContainerHighest = Color(0xFF343860),
    outline = Color(0xFF90909A),
)

@Composable
fun animatedColorScheme(isDarkMode: Boolean): ColorScheme {
    val target = if (isDarkMode) DarkColors else LightColors

    return target.copy(
        primary = animatedColor(target.primary),
        onPrimary = animatedColor(target.onPrimary),
        primaryContainer = animatedColor(target.primaryContainer),
        onPrimaryContainer = animatedColor(target.onPrimaryContainer),
        background = animatedColor(target.background),
        onBackground = animatedColor(target.onBackground),
        surface = animatedColor(target.surface),
        onSurface = animatedColor(target.onSurface),
        surfaceVariant = animatedColor(target.surfaceVariant),
        onSurfaceVariant = animatedColor(target.onSurfaceVariant),
        surfaceContainerHighest = animatedColor(target.surfaceContainerHighest),
        outline = animatedColor(target.outline),
        error = animatedColor(target.error),
    )
}

@Composable
private fun animatedColor(target: Color): Color =
    animateColorAsState(
        targetValue = target,
        animationSpec = tween(durationMillis = THEME_ANIMATION_MILLIS),
        label = "themeColor",
    ).value