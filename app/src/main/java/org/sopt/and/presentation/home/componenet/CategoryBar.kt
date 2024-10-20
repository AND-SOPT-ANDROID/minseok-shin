package org.sopt.and.presentation.home.componenet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import org.sopt.and.R

@Composable
fun CategoryBar(modifier: Modifier = Modifier) {
    val categories = listOf(
        R.string.home_category_classic,
        R.string.home_category_drama,
        R.string.home_category_variety,
        R.string.home_category_movie,
        R.string.home_category_anime,
        R.string.home_category_foreign_series
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        categories.forEach { categoryResId ->
            Text(
                text = stringResource(id = categoryResId),
                color = Color.LightGray,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}