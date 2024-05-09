package com.example.androidapp.Client.Waiting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.Client.Basket.BasketItems
import com.example.androidapp.api.dto.Category
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.api.dto.OrderItemDto
import com.example.androidapp.api.dto.Status
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun Waiting(navController: NavController){
    val status = Status.DELIVERED
    val sampleData = listOf(
        OrderItemDto(1, MenuDto(
            1,
            "Schabowy z ziemniaczkami",
            "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
            listOf("gluten"),
            21.20,
            Category.BREAKFAST,
            "https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v",
            listOf("https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v")
        ), 2),
        OrderItemDto(2, MenuDto(
            12,
            "Pomidor haha",
            "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
            listOf("gluten"),
            1.34,
            Category.BREAKFAST,
            "https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v",
            listOf("https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v")
        ), 1),
        OrderItemDto(3, MenuDto(
            2,
            "Piwo Piwo Piwo",
            "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
            listOf("gluten"),
            5.15,
            Category.BREAKFAST,
            "https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v",
            listOf("https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v")
        ), 6),
        OrderItemDto(4, MenuDto(
            3,
            "Barszcz z uszkami",
            "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
            listOf("gluten"),
            21.20,
            Category.BREAKFAST,
            "https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v",
            listOf("https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v")
        ), 2),
        OrderItemDto(5, MenuDto(
            5,
            "Kompot ze śliwką",
            "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
            listOf("gluten"),
            21.20,
            Category.BREAKFAST,
            "https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v",
            listOf("https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v")
        ), 3)
    )
    val calculatedTime = 600000L //podane w milisekundach


    Column (modifier = Modifier
        .fillMaxHeight()
        .padding(16.dp)){
        when (status) {
            Status.PLACED -> {
                OrderPlaced()
            }
            Status.IN_PROGRESS -> {
                OrderInProgress()
            }
            Status.DELIVERED -> {
                OrderDelivered()
            }
            else -> {}
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Divider()

        Spacer(modifier = Modifier.padding(16.dp))

        when (status) {
            Status.PLACED -> {
                BasketItems(items = sampleData)
            }
            Status.IN_PROGRESS -> {
                Spacer(modifier = Modifier.padding(32.dp))
                CountDown(totalTime = calculatedTime,
                    handleColor = Color.Red,
                    inactiveBarColor = Color.DarkGray,
                    activeBarColor = Color(0xFFCC0000),
                    modifier = Modifier.size(200.dp).align(Alignment.CenterHorizontally))
            }
            Status.DELIVERED -> {
                BasketItems(items = sampleData)
            }
            else -> {}
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidAppTheme {
        Waiting(navController = rememberNavController())
    }
}