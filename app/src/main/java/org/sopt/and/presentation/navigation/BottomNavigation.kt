package org.sopt.and.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.and.Route

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem> = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.MyPage
    ),
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    Column (modifier = modifier.background(color = Color(0xFF1B1B1B))){
        Text(
            text = "첫 결제 시 첫 달 100원!",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF0281ED),
                            Color(0xFF02B9B5)
                        )
                    ),
                )
                .padding(vertical = 15.dp)
        )

        NavigationBar(
            modifier = modifier,
            containerColor = Color.Black
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        when (item) {
                            is BottomNavItem.MyPage -> {
                                navController.navigate(Route.MyPage(email = "")) {
                                    popUpTo(0) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }

                            else -> {
                                navController.navigate(item.route) {
                                    popUpTo(0) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        }
                    },
                    icon = {
                        Column(horizontalAlignment = CenterHorizontally) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name,
                                tint = if (selected) Color.White else Color.LightGray
                            )
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = if (selected) Color.White else Color.LightGray
                            )
                        }
                    }
                )
            }
        }
    }
}


@Preview
@Composable
private fun NavPreview() {


    val navController = rememberNavController()

    BottomNavigationBar(
        navController = navController
    )
}
