package org.sopt.and.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun MyPagePurchaseBox(modifier: Modifier=Modifier,description: String) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFF252525))
            .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 25.dp)
    ) {

        Text(text = description, color = Color(0xFFA5A5A5),
            modifier = modifier.padding(bottom = 5.dp))
        Row (verticalAlignment = Alignment.CenterVertically){
            Text(text = stringResource(R.string.my_page_purchase), color = Color.White)
            Column {
                Spacer(modifier = Modifier.height(2.dp))
                Icon(
                    painterResource(id = R.drawable.ic_my_page_arrow_forward_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(15.dp),
                )
            }
        }

    }
}

@Composable
fun MyPageBox(modifier: Modifier=Modifier,title:String,description: String) {
    Column (modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 11.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        )
        Spacer(modifier = modifier.height(40.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_all_info_24),
            tint = Color(0xFFACACAC),
            contentDescription = null,
            modifier = modifier.size(60.dp)
        )
        Spacer(modifier = modifier.height(10.dp))
        Text(
            text = description,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color(0xFFACACAC)
        )

        Spacer(modifier = modifier.height(40.dp))
    }
    
}

@Preview
@Composable
fun MyPageBoxPreView() {
    Column {
        MyPagePurchaseBox(description = "첫 결제 시 첫 달 100원!")
        MyPageBox(title = stringResource(R.string.my_page_view_history), description = stringResource(R.string.my_page_view_history_description))
    }
}