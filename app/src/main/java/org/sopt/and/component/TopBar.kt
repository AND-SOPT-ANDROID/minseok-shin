package org.sopt.and.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun TopBar(
    text: String,
    @DrawableRes id: Int,
    alignment: Alignment
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(alignment)
                .padding(16.dp)
        )
    }
}



@Preview
@Composable
private fun TopBarPreview() {
    Column {
        TopBar("회원가입", R.drawable.ic_top_bar_close, Alignment.CenterStart)
        TopBar("회원가입", R.drawable.ic_top_bar_close, Alignment.CenterEnd)
    }
}
