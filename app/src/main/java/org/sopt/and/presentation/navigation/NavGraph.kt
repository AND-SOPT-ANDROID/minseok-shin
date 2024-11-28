package org.sopt.and.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.sopt.and.presentation.home.HomeScreen
import org.sopt.and.presentation.mypage.MyPageScreen
import org.sopt.and.presentation.search.SearchScreen
import org.sopt.and.presentation.signin.SignInScreen
import org.sopt.and.presentation.signup.SignUpScreen
import org.sopt.and.presentation.util.Route

@Composable
fun NavGraph(
    navController: NavHostController,
    isLogined: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = navController.context
    NavHost(
        navController = navController,
        startDestination = Route.SignIn(email = "", password = ""),
        modifier = modifier
    ) {
        composable<Route.Home> {
            HomeScreen()
        }

        composable<Route.SignIn> { backStackEntry ->
            val item = backStackEntry.toRoute<Route.SignIn>()
            SignInScreen(
                navigateToMyPage = { email ->
                    navController.navigate(Route.MyPage(email.toString())) {
                        popUpTo<Route.SignIn> {
                            inclusive = true
                        }
                    }
                    isLogined(true)
                },
                navigateToSignUp = {
                    navController.navigate(Route.SignUp)
                },
            )
        }

        composable<Route.SignUp> {
            SignUpScreen(
                navigateToSignIn = { user ->
                    navController.navigate(Route.SignIn(user.email, user.password)) {
                        popUpTo(Route.SignUp) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Route.Search> {
            SearchScreen()
        }

        composable<Route.MyPage> { backStackEntry ->
            val item = backStackEntry.toRoute<Route.MyPage>()
            MyPageScreen()
        }

    }
}

