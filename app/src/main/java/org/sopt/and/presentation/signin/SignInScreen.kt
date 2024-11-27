package org.sopt.and.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.presentation.component.BaseTextField
import org.sopt.and.presentation.component.PasswordTextField
import org.sopt.and.presentation.component.TopBar
import org.sopt.and.util.noRippleClickable

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel,
    modifier: Modifier = Modifier,
    navigateToSignUp: () -> Unit = {},
    navigateToMyPage: (String) -> Unit = {},
) {
    val userEmail = remember { mutableStateOf("") }
    val userPassword = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutine = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        signInViewModel.uiEvent.collect { event ->
            when (event) {
                is SignInViewModel.SignInEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }

                is SignInViewModel.SignInEvent.NavigateToMyPage -> {
                    navigateToMyPage(event.email)
                }
            }
        }
    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
    ) {
        TopBar(
            text = stringResource(id = R.string.all_app_title),
            id = R.drawable.ic_top_bar_arrow_back_24,
            alignment = Alignment.CenterStart
        )
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.dp)
        ) {
            BaseTextField(
                text = userEmail,
                placeHolder = stringResource(id = R.string.sign_in_email_placeholder),
                onValueChange = {
                    userEmail.value = it
                    signInViewModel.onEmailChanged(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextField(
                userPassword = userPassword,
                passwordVisible = passwordVisible,
                onValueChange = {
                    userPassword.value = it
                    signInViewModel.onPasswordChanged(it)
                },
                placeHolder = stringResource(id = R.string.sign_in_password_placeholder),
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(id = R.string.sign_in_button_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF1353FA), shape = RoundedCornerShape(25.dp))
                    .padding(vertical = 15.dp)
                    .noRippleClickable {
                        coroutine.launch {
                            signInViewModel.signIn()
                        }
                    },
                textAlign = TextAlign.Center,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(id = R.string.find_id_password),
                    color = Color(0xFFB0B0B0),
                    fontSize = 12.sp
                )
                Text(
                    text = stringResource(id = R.string.navigation_to_sign_up),
                    color = Color(0xFFB0B0B0),
                    fontSize = 12.sp,
                    modifier = Modifier.noRippleClickable {
                        navigateToSignUp()
                    })
            }
            Spacer(modifier = modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.img_sign_up_image),
                contentDescription = "로그인 이미지",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            SnackbarHost(hostState = snackBarHostState)
        }
    }

}

//@Preview
//@Composable
//fun SignInScreenPreview() {
//    SignInScreen()
//}
