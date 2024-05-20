package com.example.androidapp.Client.Menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.CustomerViewModel
import com.example.androidapp.R
import com.example.androidapp.Utils.Nav
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
    val viewModel: CustomerViewModel = CustomerViewModel.current
    val selectedTabIndex = viewModel.selectedTabIndex
    val isSomethingInCart = viewModel.cart.value.isNotEmpty()
    val cartValue = viewModel.cart.value.sumOf { it.menuItem.price * it.quantity }
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                viewModel.selectedTabIndex = index
            }
    }

    Box(modifier = Modifier.fillMaxSize()) {
    Column (modifier = Modifier.padding(bottom = if(isSomethingInCart) 50.dp else 0.dp)) {
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
                            R.string.BREAKFAST -> viewModel.menu.value?.filter { it.category == Category.BREAKFAST }
                                ?: listOf()

                            R.string.LUNCH -> viewModel.menu.value?.filter { it.category == Category.LUNCH }
                                ?: listOf()

                            R.string.DESERT -> viewModel.menu.value?.filter { it.category == Category.DESSERT }
                                ?: listOf()

                            R.string.DRINKS -> viewModel.menu.value?.filter { it.category == Category.DRINKS }
                                ?: listOf()

                            R.string.ADDITIONALS -> viewModel.menu.value?.filter { it.category == Category.ADDITIONALS }
                                ?: listOf()

                            else -> listOf()
                        }, navController
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }
        }

    }
        if(isSomethingInCart){
            Button(
                onClick = { navController.navigate(Nav.Basket.route) },
                colors = ButtonDefaults.buttonColors(Color(0xFF0D47A1)),
                shape = RectangleShape,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_shopping_bag_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Przejdź do koszyka | ($cartValue zł)",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                    )
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