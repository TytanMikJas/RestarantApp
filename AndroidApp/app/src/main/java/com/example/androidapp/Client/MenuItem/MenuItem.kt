package com.example.androidapp.Client.MenuItem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidapp.Client.Menu.MenuItemDto
import com.example.androidapp.ui.theme.AndroidAppTheme


@Composable
fun MenuItem(menuItem: MenuItemDto) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top
        ) {
//            VideoPlayer(videoUrl = menuItem.video)
            ImageSlider(menuItem = menuItem)

            ExpandableTextBox(text = menuItem.description)

        }
        val addButtonShape = RoundedCornerShape(50.dp)

        FloatingActionButton(
            onClick = { TODO() },
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
                .border(2.dp, color = MaterialTheme.colorScheme.primary, addButtonShape)
                .clip(addButtonShape)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Dodaj do zamówienia"
            )

        }
    }
}




@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    val menuItem = MenuItemDto(
                    3,
                    "Kotlet schabowy",
                    "Kotlet schabowy z ziemniakami i surówką",
                    listOf("gluten"),
                    18.50f,
                    listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg", "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg"),
                    "https://example.com/video.mp4",
                    "LUNCH")
    AndroidAppTheme {
        MenuItem(menuItem)
    }
}