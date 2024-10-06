package org.sopt.and

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 30.dp),
    ) {
        val userId = remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }
        Spacer(Modifier.height(10.dp))
        Text(
            text = "Welcome To SOPT",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.weight(0.5f))
        Text("ID")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userId.value,
            onValueChange = { newValue -> userId.value = newValue },
            placeholder = { Text("사용자 이름 입력") },
            maxLines = 1
        )
        Spacer(Modifier.height(20.dp))
        Text("비밀번호")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userPassword,
            onValueChange = { newValue -> userPassword = newValue },
            placeholder = { Text("비밀번호 입력") },
            maxLines = 1

        )
        Spacer(Modifier.weight(1f))
        Text(
            text = "회원가입 하기",
            modifier = Modifier
                .clickable {
                    navigateToSignUpScreen(context = context)
                }
                .fillMaxWidth(),
            style = TextStyle(
                textDecoration = TextDecoration.Underline
            ),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF455F92),
                contentColor = Color.White
            )
        ) {
            Text("로그인하기", modifier = Modifier)
        }
        Spacer(Modifier.height(30.dp))

    }
}

@Preview
@Composable
fun MainViewPreview() {
    MainScreen(
        modifier = Modifier
    )
}

private fun navigateToSignUpScreen(context: Context) {
    val intent = Intent(context, SignUpActivity::class.java)
    context.startActivity(intent)
}