package org.sopt.and

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.and.User.EMAIL
import org.sopt.and.User.PASSWORD
import org.sopt.and.component.EmailTextField
import org.sopt.and.component.LeftButtonTopBar
import org.sopt.and.component.PasswordTextField
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val email = intent.getStringExtra(EMAIL) ?: ""
        val password = intent.getStringExtra(PASSWORD) ?: ""

        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignInScreen(
                        modifier = Modifier.padding(innerPadding),
                        email = email,
                        password = password,
                        onSignInSuccess = { userEmail ->
                            navigateToMyPageScreen(userEmail)
                        },
                        navigateToSignUpScreen = {
                            navigateToSignUpScreen()
                        }
                    )
                }
            }
        }
    }

    private fun navigateToMyPageScreen(email: String) {
        val intent = Intent(this, MyActivity::class.java).apply {
            putExtra(EMAIL, email)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }

    private fun navigateToSignUpScreen() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun SignInScreen(
    modifier: Modifier,
    email: String,
    password: String,
    onSignInSuccess: (String) -> Unit,
    navigateToSignUpScreen: () -> Unit
) {

    val userEmail = remember { mutableStateOf("") }
    val userPassword = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutine = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
    ) {

        LeftButtonTopBar(
            text = stringResource(id = R.string.sign_in_top_bar),
            R.drawable.ic_top_bar_arrow_back_24
        )
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.dp)
        ) {
            EmailTextField(
                userEmail = userEmail,
                placeHolder = stringResource(id = R.string.sign_in_email_placeholder)
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextField(
                userPassword = userPassword,
                passwordVisible = passwordVisible,
                placeHolder = stringResource(id = R.string.sign_in_password_placeholder)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(id = R.string.sign_in_button_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF1353FA), shape = RoundedCornerShape(25.dp))
                    .padding(vertical = 15.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if (email.isNotEmpty() && email == userEmail.value && password == userPassword.value) {
                            coroutine.launch {
                                snackBarHostState.showSnackbar(message = context.getString(R.string.sign_in_success))
                                delay(500)
                                onSignInSuccess(userEmail.value)
                            }
                        } else {
                            coroutine.launch {
                                snackBarHostState.showSnackbar(message = context.getString(R.string.sign_in_failed))
                            }
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
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        navigateToSignUpScreen()
                    }
                )
            }
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


@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        modifier = Modifier,
        email = "",
        password = "",
        onSignInSuccess = {},
        navigateToSignUpScreen = {}
    )
}


