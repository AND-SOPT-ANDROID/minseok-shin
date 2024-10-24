package org.sopt.and.presentation.home

import androidx.lifecycle.ViewModel
import org.sopt.and.R


class HomeViewModel : ViewModel() {
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

}