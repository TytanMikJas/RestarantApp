package com.example.androidapp.Client.Menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androidapp.api.dto.Category
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun ItemCard(menuItem: MenuDto, navController: NavController) {
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(menuItem.images.first())
                    .crossfade(true)
                    .build(),
                contentDescription = menuItem.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = menuItem.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = menuItem.description,
                    style = MaterialTheme.typography.bodySmall,
                )
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = menuItem.price.toString() + "0 zł",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemCard() {
    val menuItemDtoExample = MenuDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20,
        Category.BREAKFAST,
        "https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v",
        listOf("https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v")
    )
    AndroidAppTheme {
        ItemCard(menuItemDtoExample, rememberNavController())
    }
}
