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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.LocalExampleViewModel
import com.example.androidapp.R
import com.example.androidapp.api.dto.Category
import com.example.androidapp.ui.theme.AndroidAppTheme
import com.example.androidapp.viewmodels.customer.CustomerViewModel
import kotlinx.coroutines.launch

val menuCategories = listOf(
    R.string.BREAKFAST,
    R.string.LUNCH,
    R.string.DESERT,
    R.string.DRINKS,
    R.string.ADDITIONALS,
)

@Composable
fun Menu(navController: NavController) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val viewModel: CustomerViewModel = LocalExampleViewModel.current
    val selectedTabIndex = viewModel.selectedTabIndex

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                viewModel.selectedTabIndex = index
            }
    }


    Column {
        ScrollableTabRow(selectedTabIndex = selectedTabIndex, edgePadding = 16.dp) {
            menuCategories.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        viewModel.selectedTabIndex = index
                        coroutineScope.launch {
                            listState.scrollToItem(index)
                        }
                    },
                    text = { Text(stringResource(id = title)) }
                )
            }
        }

        LazyColumn(state = listState, modifier = Modifier.padding(8.dp)) {
            menuCategories.forEachIndexed { _, title ->
                item {
                    MenuCategory(
                        stringResource(id = title), when (title) {
                            R.string.BREAKFAST -> viewModel.menu.value?.filter { it.category == Category.BREAKFAST } ?: listOf()
                            R.string.LUNCH -> viewModel.menu.value?.filter { it.category == Category.LUNCH } ?: listOf()
                            R.string.DESERT -> viewModel.menu.value?.filter { it.category == Category.DESSERT } ?: listOf()
                            R.string.DRINKS -> viewModel.menu.value?.filter { it.category == Category.DRINKS } ?: listOf()
                            R.string.ADDITIONALS -> viewModel.menu.value?.filter { it.category == Category.ADDITIONALS } ?: listOf()
                            else -> listOf()
                        }, navController
                    )
                    Spacer(modifier = Modifier.height(14.dp))
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