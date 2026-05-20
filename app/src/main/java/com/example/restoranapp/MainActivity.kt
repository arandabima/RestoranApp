package com.example.restoranapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.restoranapp.data.PreferencesManager
import com.example.restoranapp.navigation.AppNavigation
import com.example.restoranapp.ui.theme.RestoranAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val prefsManager = remember { PreferencesManager(context) }
            var darkMode by remember { mutableStateOf(prefsManager.isDarkMode()) }

            RestoranAppTheme(darkTheme = darkMode) {
                AppNavigation(
                    darkMode = darkMode,
                    onToggleDarkMode = {
                        darkMode = prefsManager.toggleDarkMode()
                    }
                )
            }
        }
    }
}