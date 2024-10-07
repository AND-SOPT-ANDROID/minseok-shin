package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.component.RightButtonTopBar
import org.sopt.and.ui.theme.ANDANDROIDTheme
import java.util.regex.Pattern

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var buttonClickable by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(userEmail, userPassword) {
        buttonClickable = isValidEmail(userEmail) && isValidPassword(userPassword)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
    ) {
        RightButtonTopBar(
            text = stringResource(id = R.string.sign_up),
            R.drawable.ic_top_bar_close
        )
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.dp)
        ) {
            Text(
                text = stringResource(id = R.string.introduction),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = userEmail,
                onValueChange = { newValue -> userEmail = newValue },
                placeholder = { Text(text = stringResource(id = R.string.email_placeholder)) },
                maxLines = 1,
            )
            Text(
                text = stringResource(id = R.string.email_description),
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = userPassword,
                onValueChange = { newValue -> userPassword = newValue },
                placeholder = { Text(text = stringResource(id = R.string.password_placeholder)) },
                maxLines = 1,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Text(
                        text = stringResource(id = if (passwordVisible) R.string.password_visible_button_hide else R.string.password_visible_button_show),
                        modifier = Modifier
                            .clickable {
                                passwordVisible = !passwordVisible
                            }
                            .padding(end = 10.dp),
                        color = Color.Gray
                    )
                }
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

        Text(
            text = stringResource(id = R.string.sign_up_button_text),
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = buttonClickable) {

                }
                .background(
                    color = if (buttonClickable) Color.DarkGray else Color.LightGray
                )
                .padding(vertical = 20.dp),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}

fun isValidEmail(email: String): Boolean {
    val emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return emailPattern.matcher(email).matches()
}

fun isValidPassword(password: String): Boolean {
    if (password.length !in 8..20) {
        return false
    }

    val lowercase = password.count { it.isLowerCase() }
    val uppercase = password.count { it.isUpperCase() }
    val digit = password.count { it.isDigit() }
    val specialChar = password.count { it in "!@#\$%^&*() _+\\-=\\[\\]{};':\"\\\\|,.<>\\/?" }

    val characterTypesCount =
        listOf(lowercase > 0, uppercase > 0, digit > 0, specialChar > 0).count { it }

    return characterTypesCount >= 3
}
