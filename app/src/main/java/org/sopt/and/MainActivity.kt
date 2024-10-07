package org.sopt.and

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val context = LocalContext.current
                var isLoggedIn by remember { mutableStateOf(false) }
                if (isLoggedIn) {
                    navigateToMyPageScreen(context = context)
                } else {
                    navigateToSignInScreen(context = context)
                }
            }
        }
    }
}


private fun navigateToSignInScreen(context: Context) {
    val intent = Intent(context, SignInActivity::class.java)
    context.startActivity(intent)
}

private fun navigateToMyPageScreen(context: Context) {
    val intent = Intent(context, MyActivity::class.java)
    context.startActivity(intent)
}
