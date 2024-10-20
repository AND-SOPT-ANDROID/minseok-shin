package org.sopt.and.presentation.home.componenet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.sopt.and.R

@Composable
fun BannerViewPager(modifier: Modifier = Modifier, pagerState: PagerState, images: List<Int>) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
    ) { page ->
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(450.dp)
        ) {
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.Center
            )
            BannerIndexTag(
                index = page,
                size = images.size,
                modifier = modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Preview
@Composable
private fun BannerPreview() {
    val images = listOf(
        R.drawable.img_home_banner1,
        R.drawable.img_home_banner2,
        R.drawable.img_home_banner3,
        R.drawable.img_home_banner4
    )

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    BannerViewPager(pagerState = pagerState, images = images)
}