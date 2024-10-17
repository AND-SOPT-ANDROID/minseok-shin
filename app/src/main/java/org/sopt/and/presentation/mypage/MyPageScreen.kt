package org.sopt.and.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.presentation.component.MyPageBox
import org.sopt.and.presentation.component.MyPagePurchaseBox


@Composable
fun MyPageScreen(modifier: Modifier = Modifier, myPageViewModel: MyPageViewModel = viewModel()) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B)),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color(0xFF252525))
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_my_page_person_24),
                contentDescription = null,
                tint = Color.White,
                modifier = modifier
                    .padding(end = 10.dp)
                    .size(60.dp)
            )
            Text(
                text = myPageViewModel.user.value?.email ?: "",
                color = Color.White,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.weight(1f)
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_my_page_bell_24),
                contentDescription = null,
                tint = Color.White,
                modifier = modifier
                    .padding(end = 20.dp)
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_my_page_settings_24),
                contentDescription = null,
                tint = Color.White
            )
        }
        MyPagePurchaseBox(description = stringResource(R.string.my_page_first_box_description))
        Spacer(modifier = modifier.height(1.dp))
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
    MyPageScreen()
}

