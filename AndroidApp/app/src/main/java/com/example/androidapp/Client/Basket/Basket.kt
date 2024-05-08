package com.example.androidapp.Client.Basket

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.api.dto.Category
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.api.dto.OrderItemDto
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun Basket(navController: NavController) {
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


    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxHeight()) {

        Text(
            text = "Podsumowanie zamówienia",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(sampleData) { item ->
                Row {
                    Text(
                        text = "${item.quantity} x ${item.menuItem.name}",
                        modifier = Modifier.weight(1f),
                        style = TextStyle(
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "${String.format("%.2f", item.menuItem.price * item.quantity)}zł",
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 20.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
            }
        }

        val total = sampleData.fold(0.0) { acc, item ->
            acc + item.quantity * item.menuItem.price
        }


        Divider()
        Spacer(modifier = Modifier.height(14.dp))

        Row(){
            Text(text = "Razem:",
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    fontSize = 20.sp
                ))

            Text(text = "$total zł",
                style = TextStyle(
                fontSize = 20.sp
            ))
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* TODO: Handle order action */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(50) // Circular edges
        ) {
            Text("Zamów", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidAppTheme {
        Basket(navController = rememberNavController())
    }
}