package org.sopt.and.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    userEmail: MutableState<String>,
    placeHolder: String
) {
    val containerColor = Color(0xFF2F2F2F)
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = userEmail.value,
        onValueChange = { newValue -> userEmail.value = newValue },
        placeholder = {
            Text(
                text = placeHolder,
                color = Color(0xFFABABAB)
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(5.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            unfocusedBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        )
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    userPassword: MutableState<String>, placeHolder: String,
    passwordVisible: MutableState<Boolean>
) {
    val containerColor = Color(0xFF2F2F2F)
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = userPassword.value,
        onValueChange = { newValue -> userPassword.value = newValue },
        placeholder = {
            Text(
                text = placeHolder,
                color = Color(0xFFABABAB)
            )
        },
        maxLines = 1,
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            Text(
                text = stringResource(id = if (passwordVisible.value) R.string.password_visible_button_hide else R.string.password_visible_button_show),
                modifier = modifier
                    .clickable {
                        passwordVisible.value = !passwordVisible.value
                    }
                    .padding(end = 10.dp),
                color = Color.Gray
            )
        },
        shape = RoundedCornerShape(5.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            unfocusedBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        )
    )
}

@Preview
@Composable
private fun TextFieldPreview() {
    val userEmail = remember { mutableStateOf("") }
    val userPassword = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    Column {
        EmailTextField(
            userEmail = userEmail,
            placeHolder = stringResource(id = R.string.sign_up_email_placeholder)
        )
        PasswordTextField(
            userPassword = userPassword,
            passwordVisible = passwordVisible,
            placeHolder = stringResource(id = R.string.sign_up_password_placeholder)
        )
    }
}
