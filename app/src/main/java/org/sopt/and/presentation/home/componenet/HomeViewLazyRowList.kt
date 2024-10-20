package org.sopt.and.presentation.home.componenet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun HomeViewLazyRow(images: List<Int>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        items(images) { imageRes ->
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .width(LocalConfiguration.current.screenWidthDp.dp * 0.29f)
                    .padding(horizontal = 5.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
fun HomeViewTop20LazyRow(images: List<Int>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        items(images) { imageRes ->
            Box {
                Column {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .width(LocalConfiguration.current.screenWidthDp.dp * 0.43f)
                            .padding(horizontal = 5.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = modifier.height(20.dp))
                }
                Text(
                    text = "${images.indexOf(imageRes) + 1}",
                    fontSize = 45.sp,
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 18.dp)
                        .shadow(20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun LazyRowPreview() {
    val recommendImages = listOf(
        R.drawable.img_home_content1,
        R.drawable.img_home_content2,
        R.drawable.img_home_content3,
        R.drawable.img_home_content4,
        R.drawable.img_home_content5,
        R.drawable.img_home_content6
    )
    Column {
        HomeViewLazyRow(recommendImages)
        HomeViewTop20LazyRow(recommendImages)
    }
}
