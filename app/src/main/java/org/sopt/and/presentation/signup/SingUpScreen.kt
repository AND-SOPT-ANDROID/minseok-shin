package org.sopt.and.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.domain.User
import org.sopt.and.presentation.component.EmailTextField
import org.sopt.and.presentation.component.PasswordTextField
import org.sopt.and.presentation.component.TopBar
import org.sopt.and.presentation.signin.SignInViewModel
import org.sopt.and.util.noRippleClickable
import org.sopt.and.util.showToast

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = viewModel(),
    navigateToSignIn: (user: User) -> Unit = {},
    signInViewModel: SignInViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val userEmail = remember { mutableStateOf("") }
    val userPassword = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    val buttonClickable by signUpViewModel.buttonClickable.collectAsState(false)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
    ) {
        TopBar(
            text = stringResource(id = R.string.sign_up_top_bar),
            id = R.drawable.ic_top_bar_close,
            alignment = Alignment.CenterEnd
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.dp)
        ) {
            Text(
                text = stringResource(id = R.string.introduction), color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            EmailTextField(userEmail = userEmail,
                placeHolder = stringResource(id = R.string.sign_up_email_placeholder),
                onValueChange = {
                    userEmail.value = it
                    signUpViewModel.onEmailChanged(it)
                })
            Text(
                text = stringResource(id = R.string.email_description),
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            PasswordTextField(
                userPassword = userPassword, passwordVisible = passwordVisible, onValueChange = {
                    userPassword.value = it
                    signUpViewModel.onPasswordChanged(it)
                }, placeHolder = stringResource(id = R.string.sign_up_password_placeholder)
            )
            Text(
                text = stringResource(id = R.string.password_description),
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
        Image(
            painter = painterResource(id = R.drawable.img_sign_up_image),
            contentDescription = "회원가입 이미지",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(text = stringResource(id = R.string.sign_up_button_text),
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable(
                    enabled = buttonClickable
                ) {
                    signUpViewModel.signUp(userEmail.value, userPassword.value)
                    signInViewModel.updateUser(userEmail.value, userPassword.value)
                    navigateToSignIn(User(userEmail.value, userPassword.value))
                    context.showToast(context.getString(R.string.sign_up_success))
                }
                .background(
                    color = if (buttonClickable) Color.DarkGray else Color.LightGray
                )
                .padding(vertical = 20.dp),
            textAlign = TextAlign.Center,
            color = Color.White)
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}


