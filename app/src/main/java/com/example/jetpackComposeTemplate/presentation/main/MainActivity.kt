package com.example.jetpackComposeTemplate.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.jetpackComposeTemplate.presentation.navHost.MyAppNavigation
import com.example.jetpackComposeTemplate.provider.resource.ResourceProvider
import com.example.jetpackComposeTemplate.ui.theme.jetpackComposeTemplateTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    @Inject
    lateinit var resourceProvider: ResourceProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install Splash Screen
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            // Example: Wait for some initialization logic
            false // Set this to true if you want to delay the splash screen
        }
        enableEdgeToEdge()
        setContent {
            jetpackComposeTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppNavigation(
                        modifier = Modifier.padding(innerPadding),
                        resourceProvider = resourceProvider
                    )
                }
            }
        }
    }
}


