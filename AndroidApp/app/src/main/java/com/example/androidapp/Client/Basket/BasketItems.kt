package com.example.androidapp.Client.Basket

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidapp.api.dto.OrderItemDto

@Composable
fun BasketItems(items: List<OrderItemDto>){
    LazyColumn {
        items(items) { item ->
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

    val total = items.fold(0.0) { acc, item ->
        acc + item.quantity * item.menuItem.price
    }


    Divider()
    Spacer(modifier = Modifier.height(14.dp))

    Row(){
        Text(text = "Razem:",
            modifier = Modifier.weight(1f),
            style = TextStyle(
                fontSize = 20.sp
            )
        )

        Text(text = "$total zł",
            style = TextStyle(
                fontSize = 20.sp
            )
        )
    }

}