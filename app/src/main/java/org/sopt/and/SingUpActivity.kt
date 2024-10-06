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
        RightButtonTopBar(text = "회원가입")
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.dp)
        ) {
            Text(
                text = "이메일과 비밀번호만으로\nWavve를 즐길 수 있어요!",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = userEmail,
                onValueChange = { newValue -> userEmail = newValue },
                placeholder = { Text("wavve@example.com") },
                maxLines = 1,
            )
            Text(
                text = "! 로그인, 비말번호 찾기, 알림에 사용되니 정확한 이메일을 입력해\n주세요.",
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = userPassword,
                onValueChange = { newValue -> userPassword = newValue },
                placeholder = { Text("Wavve 비밀번호 설정") },
                maxLines = 1,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Text(
                        text = if (passwordVisible) "Hide" else "Show",
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
                text = "! 비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중\n3가지 이상 혼용하여 입력해 주세요.",
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
            text = "Wavve 회원가입",
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

    val characterTypesCount = listOf(lowercase > 0, uppercase > 0, digit > 0, specialChar > 0).count { it }

    return characterTypesCount >= 3
}
