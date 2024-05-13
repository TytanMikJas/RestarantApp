package com.example.androidapp.Client.LandingPage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.CustomerViewModel
import com.example.androidapp.R
import com.example.androidapp.ui.theme.AndroidAppTheme
import com.example.androidapp.Utils.Nav
import com.example.androidapp.Utils.SatisfyText

val landingPageSubpages = listOf(
    R.string.RESTURANT,
    R.string.CHEF,
    R.string.TEAM
)


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LandingPage(navController: NavController) {
    val context = LocalContext.current

    val listState = rememberLazyListState()
    var selectedIndex by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val categoryIndices = remember { mutableStateMapOf<Int, Int>() }
    val viewModel = CustomerViewModel.current
    val pagerState = rememberPagerState(
        pageCount = { landingPageSubpages.size },
        initialPage = 0,
        initialPageOffsetFraction = 0f
    )

    LaunchedEffect(selectedIndex) {
        pagerState.scrollToPage(selectedIndex)
    }



    LaunchedEffect(pagerState.currentPage) {
        selectedIndex = pagerState.currentPage
    }
    Box(modifier = Modifier.fillMaxWidth())
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            SatisfyText(
                text = "\"Pod Polakiem\"",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 50.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            SatisfyText(
                text = "Polskie - znaczy smaczne",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            TabRow(
                modifier = Modifier.padding(top = 30.dp),
                selectedTabIndex = selectedIndex,
                contentColor = MaterialTheme.colorScheme.onBackground,
                containerColor = MaterialTheme.colorScheme.background
            ) {
                landingPageSubpages.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        text = { Text(stringResource(id = title)) }
                    )
                }
            }

            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState
            ) { currentPage ->
                when (currentPage) {
                    0 -> AboutRestaurant(description = RestaurantDescription)
                    1 -> AboutChef(description = ChefDescription)
                    2 -> AboutTeam(descriptions = TeamDescriptions)
                    else -> AboutRestaurant(description = RestaurantDescription)
                }
            }
        }

        val buttonShape = RoundedCornerShape(50.dp)
        
        FloatingActionButton(
            onClick = { navController.navigate(Nav.Menu.route) },
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
                .border(2.dp, color = MaterialTheme.colorScheme.tertiary, buttonShape)
                .clip(buttonShape)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 60.dp),
                fontSize = 20.sp,
                text = "Menu",
                color = MaterialTheme.colorScheme.tertiary
            )

        }
    }

    BackHandler {

    }
}



@Preview(showBackground = true)
@Composable
fun PreviewClientHome(){
    AndroidAppTheme {
        LandingPage(rememberNavController())
    }
}