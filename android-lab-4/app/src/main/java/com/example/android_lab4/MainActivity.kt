package com.example.android_lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.material3.MaterialTheme
import com.example.android_lab4.ui.theme.DarkColors
import com.example.android_lab4.ui.theme.LightColors
import com.example.themeapp.ThemeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var isDarkTheme by remember {
                mutableStateOf(false)
            }

            MaterialTheme(
                colorScheme = if (isDarkTheme) DarkColors else LightColors
            ) {
                ThemeScreen(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { isDarkTheme = it }
                )
            }
        }
    }
}
