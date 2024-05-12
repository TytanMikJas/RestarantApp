package com.example.androidapp.Client.MenuItem

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androidapp.Client.Menu.MenuItemDto
import com.example.androidapp.api.dto.Category
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.ui.theme.AndroidAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(
    modifier: Modifier = Modifier,
    menuItem: MenuDto,
    pagerPaddingValues: PaddingValues = PaddingValues(end = 65.dp),
    imageCornerRadius: Dp = 16.dp,
) {

    val pagerState = rememberPagerState(
        pageCount = { menuItem.images.size },
        initialPage = 0,
        initialPageOffsetFraction = 0f
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = pagerPaddingValues,
                modifier = modifier.aspectRatio(16f / 11f)
            ) { page ->
                val scaleFactor = 1f

                Box(modifier = modifier
                    .graphicsLayer {
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                    .alpha(
                        scaleFactor.coerceIn(0f, 1f)
                    )
                    .padding(end = 8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(imageCornerRadius))) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(menuItem.images[page])
                            .crossfade(true)
                            .build(),
                        contentDescription = menuItem.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Gray),
                    )

                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageSliderPreview() {
    val menuItem = MenuDto(
        3,
        "Kotlet schabowy",
        "Kotlet schabowy z ziemniakami i surówką",
        listOf("gluten"),
        18.50,
        Category.LUNCH,
        "https://example.com/video.mp4",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg", "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg"),
        )
    AndroidAppTheme {
       ImageSlider(menuItem = menuItem)
    }
}