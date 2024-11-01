package org.sopt.and.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import org.sopt.and.R
import org.sopt.and.presentation.home.componenet.BannerViewPager
import org.sopt.and.presentation.home.componenet.CategoryBar
import org.sopt.and.presentation.home.componenet.HomeViewContentsTitle
import org.sopt.and.presentation.home.componenet.HomeViewLazyRow
import org.sopt.and.presentation.home.componenet.HomeViewTop20LazyRow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val pagerState =
        rememberPagerState(initialPage = 0, pageCount = { homeViewModel.bannerImages.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            val nextPage = (pagerState.currentPage + 1) % homeViewModel.bannerImages.size
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
            Column(
                modifier = modifier
                    .background(color = Color(0xFF1B1B1B))
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.all_app_title), fontSize = 20.sp,
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
            BannerViewPager(pagerState = pagerState, images = homeViewModel.bannerImages)
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            HomeViewContentsTitle(text = stringResource(R.string.home_recommend_title))
            HomeViewLazyRow(images = homeViewModel.recommendImages)
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            HomeViewContentsTitle(text = stringResource(R.string.home_top20_title), hasIcon = false)
            HomeViewTop20LazyRow(images = homeViewModel.top20Images)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreen()

    LazyRow(
        contentPadding = PaddingValues(all = 16.dp)
    ) {}

}
