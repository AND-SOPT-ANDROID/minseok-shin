package org.sopt.and

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.component.EmailTextField
import org.sopt.and.component.PasswordTextField
import org.sopt.and.component.RightButtonTopBar
import org.sopt.and.ui.theme.ANDANDROIDTheme
import java.util.regex.Pattern

class SignUpActivity : ComponentActivity() {
    private lateinit var signInLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signInLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            }

        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignUpSuccess = { email, password ->
                            navigateToSignInScreen(this, email, password)
                        })
                }
            }
        }
    }


}

@Composable
fun SignUpScreen(modifier: Modifier = Modifier, onSignUpSuccess: (String, String) -> Unit) {
    val context = LocalContext.current
    val userEmail = remember { mutableStateOf("") }
    val userPassword = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    val buttonClickable by remember {
        derivedStateOf {
            isValidEmail(userEmail.value) && isValidPassword(userPassword.value)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
    ) {
        RightButtonTopBar(
            text = stringResource(id = R.string.sign_up_top_bar),
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
            EmailTextField(
                userEmail = userEmail,
                placeHolder = stringResource(id = R.string.sign_up_email_placeholder)
            )
            Text(
                text = stringResource(id = R.string.email_description),
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            PasswordTextField(
                userPassword = userPassword,
                passwordVisible = passwordVisible,
                placeHolder = stringResource(id = R.string.sign_up_password_placeholder)
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
                .clickable(enabled = buttonClickable,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onSignUpSuccess(userEmail.value, userPassword.value)
                    Toast
                        .makeText(
                            context,
                            context.getString(R.string.sign_up_success),
                            Toast.LENGTH_SHORT
                        )
                        .show()
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
    SignUpScreen(onSignUpSuccess = { email, password -> {} })
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


fun navigateToSignUpScreen(context: Context) {
    Intent(context, SignUpActivity::class.java).apply {
        context.startActivity(this)
    }

}