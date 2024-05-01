package com.example.androidapp.Client.Menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun ItemCard(menuItem: MenuItemDto) {
    Card(
        modifier = Modifier.padding(8.dp),
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
    val menuItemDtoExample = MenuItemDto(
        1,
        "Schabowy z ziemniaczkami",
        "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
        listOf("gluten"),
        21.20f,
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg"),
        "https://example.com/video.mp4",
        "LUNCH"
    )
    AndroidAppTheme {
        ItemCard(menuItemDtoExample)
    }
}
