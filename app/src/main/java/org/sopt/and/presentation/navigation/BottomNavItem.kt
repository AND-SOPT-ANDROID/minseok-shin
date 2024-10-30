package org.sopt.and.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import org.sopt.and.Route


sealed class BottomNavItem(
    val name: String,
    val icon: ImageVector,
    val route: Any
) {
    data object Home :
        BottomNavItem(
            "홈",
            Icons.Default.Home,
            Route.Home
        )

    data object Search :
        BottomNavItem(
            "검색",
            Icons.Default.Search,
            Route.Search
        )


    data object MyPage :
        BottomNavItem(
            "MY",
            Icons.Default.Person,
            Route.MyPage("")
        )
}