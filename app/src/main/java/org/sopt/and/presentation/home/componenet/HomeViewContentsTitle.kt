package org.sopt.and.presentation.home.componenet

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun HomeViewContentsTitle(text:String,hasIcon:Boolean=true ,modifier: Modifier = Modifier) {
    Row (modifier=modifier.fillMaxWidth().padding(vertical = 10.dp)){
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.weight(1f))
        if (hasIcon){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_my_page_arrow_forward_24),
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}