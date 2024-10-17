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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.and.presentation.mypage.MyPageScreen
import org.sopt.and.presentation.mypage.MyPageViewModel
import org.sopt.and.presentation.signin.SignInScreen
import org.sopt.and.presentation.signin.SignInViewModel


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val signInViewModel: SignInViewModel = viewModel()
    val myPageViewModel: MyPageViewModel = viewModel()

    var isLoggedIn by remember { mutableStateOf(false) }

    Scaffold { innerPadding: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = "signIn",
            Modifier.padding(innerPadding)
        ) {
            composable("signIn") {
                SignInScreen(
                    onSignInSuccess = {
                        myPageViewModel.loadUser(user = it)
                        isLoggedIn = true
                        signInViewModel.signIn(email = it.email, password = it.password)
                        navController.navigate("myPage") {
                            popUpTo("signIn") { inclusive = true }
                        }
                    },
                    navigateToSignUpScreen = {
                        // 회원가입 화면으로 이동 (추가 구현 필요)
                    }
                )
            }
            composable("myPage") {
                MyPageScreen(myPageViewModel = myPageViewModel)
            }
        }
    }
}