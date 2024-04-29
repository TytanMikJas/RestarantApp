package com.example.androidapp.Waiter

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun OrderCard(order: Order, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(8.dp)
    ){
        Column(modifier = Modifier.padding(10.dp, 14.dp, 14.dp, 8.dp)) {
            Text(order.id, fontSize = 18.sp)
            Text(order.table, fontSize = 16.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(8.dp))
            for (item in order.items) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item.first)
                    Text(item.second)
                }
                Spacer(modifier = Modifier.height(2.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* TODO: handle click */ },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 6.dp)
            ) {
                Text("Action", color = Color.White)
            }
        }
    }
}


@Preview(showBackground = true,
    device = "spec:width=1100dp,height=693dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape")
@Composable
fun PreviewOrderCrd(){
    AndroidAppTheme {
            OrderCard(order = Order("#2341", "Stolik 15", listOf("Schabowy z ziemniakami" to "53,72zł", "Kompot śliwkowy" to "9,99zł")), backgroundColor = Color.Cyan.copy(alpha = 0.3f))
    }
}