package org.sopt.and.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.sopt.and.R
import org.sopt.and.presentation.home.componenet.BannerViewPager
import org.sopt.and.presentation.home.componenet.CategoryBar
import org.sopt.and.presentation.home.componenet.HomeViewContentsTitle
import org.sopt.and.presentation.home.componenet.HomeViewLazyRow
import org.sopt.and.presentation.home.componenet.HomeViewTop20LazyRow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val bannerImages = listOf(
        R.drawable.img_home_banner1,
        R.drawable.img_home_banner2,
        R.drawable.img_home_banner3,
        R.drawable.img_home_banner4
    )

    val recommendImages = listOf(
        R.drawable.img_home_content1,
        R.drawable.img_home_content2,
        R.drawable.img_home_content3,
        R.drawable.img_home_content4,
        R.drawable.img_home_content5,
        R.drawable.img_home_content6
    )


    val top20Images = listOf(
        R.drawable.img_home_top20_1,
        R.drawable.img_home_top20_2,
        R.drawable.img_home_top20_3,
        R.drawable.img_home_top20_4,
        R.drawable.img_home_top20_5,
        R.drawable.img_home_top20_6,
        R.drawable.img_home_top20_7,
        R.drawable.img_home_top20_8,
        R.drawable.img_home_top20_9,
        R.drawable.img_home_top20_10
    )


    val pagerState = rememberPagerState(initialPage = 0, pageCount = { bannerImages.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            val nextPage = (pagerState.currentPage + 1) % bannerImages.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
            .padding(horizontal = 14.dp)
    ) {
        stickyHeader {
            Column (
                modifier = modifier
                    .background(color = Color(0xFF1B1B1B))
            ){
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Wavve",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                CategoryBar()
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        item {
            BannerViewPager(pagerState = pagerState, images = bannerImages)
            Spacer(modifier = Modifier.height(20.dp))
            HomeViewContentsTitle(text = "믿고 보는 웨이브 에디터 추천작")
            HomeViewLazyRow(images = recommendImages)
            Spacer(modifier = Modifier.height(20.dp))
            HomeViewContentsTitle(text = "오늘의 TOP 20", hasIcon = false)
            HomeViewTop20LazyRow(images =top20Images)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreen()
}
