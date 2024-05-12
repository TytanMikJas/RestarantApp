package com.example.androidapp.Client.Menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.api.dto.Category
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun MenuCategory(category: String, menuItems: List<MenuDto>, navController: NavController) {
    Column {
        Text(
            text = category,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Divider(color = Color.Gray.copy(alpha = 0.4f),
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(0.25f))
        Spacer(modifier = Modifier.height(8.dp))
        menuItems.forEach {
            ItemCard(it, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuCategory() {
    AndroidAppTheme {
        MenuCategory("Dania główne",
            listOf(
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
        ),
            rememberNavController()
        )
    }
}