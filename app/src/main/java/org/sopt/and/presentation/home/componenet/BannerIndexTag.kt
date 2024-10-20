package org.sopt.and.presentation.home.componenet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BannerIndexTag(index:Int,size:Int,modifier: Modifier = Modifier) {
    Column(modifier = modifier.background(Color.DarkGray, shape = RoundedCornerShape(20.dp)).padding(vertical = 5.dp, horizontal = 10.dp)) {
        Text(text="${index+1} / $size", color = Color.White)
    }
}

@Preview
@Composable
private fun IndexTagPreview() {
    BannerIndexTag(1,6)
}