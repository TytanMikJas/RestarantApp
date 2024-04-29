package com.example.androidapp.Client.MenuItem

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidapp.R
import com.example.androidapp.ui.theme.AndroidAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(
    modifier: Modifier = Modifier,
    images: List<Painter>,
    pagerPaddingValues: PaddingValues = PaddingValues(horizontal = 65.dp),
    imageCornerRadius: Dp = 16.dp,
) {

    val pagerState = rememberPagerState(
        pageCount = { images.size },
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
                modifier = modifier.height(200.dp)
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
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(imageCornerRadius))) {
                    Image(
                        painter = images[page],
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                }
            }

        }
    }
}


@Composable
fun MenuItem() {
    val images = listOf(
        painterResource(id = R.drawable.pudzian),
        painterResource(id = R.drawable.pudzian2),
        painterResource(id = R.drawable.pudzian3)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.restaurant_banner),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        ImageSlider(images = images)

        DescriptionCard(text = "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas viverra, libero vitae consequat consequat, lorem enim egestas turpis, sed porttitor ligula felis et ligula. Pellentesque rutrum eros lectus. Integer imperdiet vehicula quam in lobortis. Nunc luctus ante nunc, eget pharetra est laoreet nec. Maecenas condimentum nisl in posuere sagittis. In sollicitudin convallis velit sed condimentum. Sed ac posuere augue. Nam a posuere tellus. Nunc pretium enim non magna hendrerit consectetur. Praesent vitae nisl nec erat ullamcorper tempor.\n" +
                "\n" +
                "Suspendisse dapibus enim eu lorem sagittis, non venenatis erat consequat. Ut mollis mi diam, quis dictum felis pulvinar id. Ut a justo arcu. Nunc ac lectus pharetra, commodo nibh id, pulvinar mi. Vivamus lectus lectus, venenatis quis venenatis quis, iaculis eget diam. Nam volutpat faucibus tincidunt. Aliquam ac lacus ultricies, mollis ipsum eget, feugiat nisi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Ut ultricies ultricies pretium. Nunc lobortis vitae ligula.")
    }
}

const val DEFAULT_MINIMUM_TEXT_LINE = 3
@Composable
fun DescriptionCard(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontStyle: FontStyle? = null,
    text: String,
    collapsedMaxLine: Int = DEFAULT_MINIMUM_TEXT_LINE,
    showMoreText: String = "... Show More",
    showMoreStyle: SpanStyle = SpanStyle(fontWeight = FontWeight.W500),
    showLessText: String = " Show Less",
    showLessStyle: SpanStyle = showMoreStyle,
    textAlign: TextAlign? = null
) {
    Box(contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .padding(20.dp)
    ) {
        Text(
            text = text.trim(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }

}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    AndroidAppTheme {
        MenuItem()
    }
}