package org.sopt.and.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.sopt.and.presentation.navigation.BottomNavigationBar
import org.sopt.and.presentation.navigation.NavGraph


@Composable
fun MainScreen() {
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
