package com.example.androidapp.Client.MenuItem

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidapp.CustomerViewModel
import com.example.androidapp.api.dto.Category
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.api.dto.OrderItemDto
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun MenuItem(menuItem: MenuDto) {
    var showPopup by remember { mutableStateOf(false)}
    val viewModel = CustomerViewModel.current

    if(showPopup) {
        AddItemDialog(menuItem,
            {showPopup = false},
            {quantity: Int ->
                val orderItem: OrderItemDto = OrderItemDto(null, menuItem, quantity)
                viewModel.cart.value = viewModel.cart.value.plus(orderItem)
                showPopup = false})
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 10.dp, horizontal = 8.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 90.dp),
            verticalArrangement = Arrangement.Top
        ) {
            var allergens: String = "Zawiera alergeny\n"
            menuItem.alergens.forEach{allergen -> allergens = allergens.plus("- $allergen\n")}

            if (!menuItem.video.isNullOrEmpty()) {
                Log.d("MenuItem", "Video: ${menuItem.video}")
                VideoPlayer(videoUrl = menuItem.video)
            }
            ImageSlider(menuItem = menuItem)
            Box(modifier = Modifier.padding(vertical = 20.dp, horizontal = 5.dp)) {
                ExpandableTextBox(text = menuItem.description)
            }
            if(menuItem.alergens.isNotEmpty()) {
                Box(modifier = Modifier.padding(top = 0.dp, bottom = 0.dp, start = 5.dp, end = 5.dp)) {
                    ExpandableTextBox(text = allergens,
                        collapsedMaxLine = 1,
                        showMoreText = " ")
                }
            }
        }
        val addButtonShape = RoundedCornerShape(50.dp)

        FloatingActionButton(
            onClick = { showPopup = true },
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
                .border(2.dp, color = MaterialTheme.colorScheme.tertiary, addButtonShape)
                .clip(addButtonShape)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Dodaj do zamówienia",
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
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
        MenuItem(menuItem)
    }
}