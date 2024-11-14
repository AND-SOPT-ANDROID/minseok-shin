package org.sopt.and.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.sopt.and.presentation.navigation.BottomNavigationBar
import org.sopt.and.presentation.navigation.NavGraph
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ANDANDROIDTheme {
                val navController = rememberNavController()
                var bottomNaviVisible by remember { mutableStateOf(false) }

                Scaffold(bottomBar = {
                    if (bottomNaviVisible) {
                        BottomNavigationBar(
                            navController = navController
                        )
                    }
                }) { innerPadding: PaddingValues ->
                    NavGraph(
                        navController = navController,
                        isLogined = { bottomNaviVisible = it },
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
