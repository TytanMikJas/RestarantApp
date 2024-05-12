package com.example.androidapp.Client.Menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.R
import com.example.androidapp.api.dto.Category
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.ui.theme.AndroidAppTheme
import kotlinx.coroutines.launch

val menuCategories = listOf(
    R.string.BREAKFAST,
    R.string.LUNCH,
    R.string.DESERT,
    R.string.DRINKS,
    R.string.ADDITIONALS,
)

val BreakfastItems = listOf(
    MenuDto(
    1,
    "Schabowy z ziemniaczkami",
    "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
    listOf("gluten"),
    21.20,
    Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
    listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
),
    MenuDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20,
        Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
    ),
    MenuDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20,
        Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
    )
)

val LunchItems = listOf(MenuDto(
    1,
    "Schabowy z ziemniaczkami",
    "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
    listOf("gluten"),
    21.20,
    Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
    listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
),
    MenuDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20,
        Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
    ),
    MenuDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20,
        Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
    )
)

val DesertItems = listOf(MenuDto(
    1,
    "Schabowy z ziemniaczkami",
    "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
    listOf("gluten"),
    21.20,
    Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
    listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
),
    MenuDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20,
        Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
    ),
    MenuDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20,
        Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
    )
)

val DrinkItems = listOf(MenuDto(
    1,
    "Schabowy z ziemniaczkami",
    "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
    listOf("gluten"),
    21.20,
    Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
    listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
),
    MenuDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20,
        Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
    ),
    MenuDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20,
        Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
    )
)

val AdditionalItems = listOf(MenuDto(
    1,
    "Schabowy z ziemniaczkami",
    "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
    listOf("gluten"),
    21.20,
    Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
    listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
),
    MenuDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20,
        Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
    ),
    MenuDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20,
        Category.BREAKFAST, "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg")
    )
)

@Composable
fun Menu(navController: NavController) {
    val listState = rememberLazyListState()
    var selectedTabIndex by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val categoryIndices = remember { mutableStateMapOf<Int, Int>() }
    var accumulatedItems = 0

    Column {
        ScrollableTabRow(selectedTabIndex = selectedTabIndex, edgePadding = 16.dp) {
            menuCategories.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        coroutineScope.launch {
                            categoryIndices[index]?.let { listState.scrollToItem(it) }
                        }
                    },
                    text = { Text(stringResource(id = title)) }
                )
            }
        }

        LazyColumn(state = listState, modifier = Modifier.padding(8.dp)) {
            menuCategories.forEachIndexed { index, title ->
                item {
                    if (!categoryIndices.containsKey(index)) {
                        categoryIndices[index] = accumulatedItems
                    }
                    MenuCategory(
                        stringResource(id = title), when (title) {
                            R.string.BREAKFAST -> BreakfastItems
                            R.string.LUNCH -> LunchItems
                            R.string.DESERT -> DesertItems
                            R.string.DRINKS -> DrinkItems
                            R.string.ADDITIONALS -> AdditionalItems
                            else -> listOf()
                        }, navController
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    accumulatedItems++
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMenu(){
    AndroidAppTheme {
        Menu(rememberNavController())
    }
}