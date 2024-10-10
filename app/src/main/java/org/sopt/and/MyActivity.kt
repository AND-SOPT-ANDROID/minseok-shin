package org.sopt.and

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.User.EMAIL
import org.sopt.and.component.MyPageBox
import org.sopt.and.component.MyPagePurchaseBox
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val email = intent.getStringExtra(EMAIL) ?: ""
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
            .background(color = Color(0xFF1B1B1B)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF252525))
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_my_page_person_24),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(60.dp)
            )
            BoxWithConstraints {
                val maxWidth = maxWidth
                Text(
                    text = email,
                    color = Color.White,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.widthIn(max = maxWidth * 0.7f)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_my_page_bell_24),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 20.dp)
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_my_page_settings_24),
                contentDescription = null,
                tint = Color.White
            )
        }
        MyPagePurchaseBox(description = stringResource(R.string.my_page_first_box_description))
        Spacer(modifier = Modifier.height(1.dp))
        MyPagePurchaseBox(description = stringResource(R.string.my_page_second_box_description))
        MyPageBox(
            title = stringResource(R.string.my_page_view_history),
            description = stringResource(R.string.my_page_view_history_description)
        )
        MyPageBox(
            title = stringResource(R.string.my_page_favorite_program),
            description = stringResource(R.string.my_page_favorite_program_description)
        )
    }
}

@Preview
@Composable
fun MyPageScreenPreview() {
    MyPageScreen(
        modifier = Modifier,
        email = "minsuk07672@naver.com"
    )
}


fun navigateToMyPageScreen(context: Context) {
    Intent(context, MyActivity::class.java).apply {
        context.startActivity(this)
    }
}


fun navigateToMyPageScreenWithData(context: Context, email: String) {
    Intent(context, MyActivity::class.java).apply {
        putExtra(EMAIL, email)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(this)
    }
}