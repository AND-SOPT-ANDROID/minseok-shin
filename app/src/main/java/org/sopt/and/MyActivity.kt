package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val email = intent.getStringExtra("EMAIL") ?: ""
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyPageScreen(
                        modifier = Modifier.padding(innerPadding),
                        email = email
                    )
                }
            }
        }
    }
}

@Composable
fun MyPageScreen(modifier: Modifier, email: String) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 30.dp),
    ) {
        Text(text = email)

    }
}

@Preview
@Composable
fun MyPageScreenPreview() {
    MyPageScreen(
        modifier = Modifier,
        email = ""
    )
}

