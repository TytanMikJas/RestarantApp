package com.example.androidapp.Waiter

import android.util.Log
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
import com.example.androidapp.WaiterViewModel
import com.example.androidapp.api.dto.OrderDto
import com.example.androidapp.api.dto.Status
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun OrderCard(order: OrderDto, backgroundColor: Color, buttonText: String) {
    val waiterViewModel = WaiterViewModel.current

    fun onClick() {
        waiterViewModel.nextOrderStatus(order.id)
        if(order.status == Status.DELIVERED){
            waiterViewModel.orders.value = waiterViewModel.orders.value.filter { it.id != order.id }
        }
        Log.d("OrderCard", "Order ${order.id} status changed")
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(8.dp)
    ){
        Column(modifier = Modifier.padding(10.dp, 14.dp, 14.dp, 8.dp)) {
            Text(order.id.toString(), fontSize = 18.sp)
            Text(order.tableId.toString(), fontSize = 16.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(8.dp))
            for (item in order.items) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item.menuItem.name)
                    Text(item.menuItem.price.toString())
                }
                Spacer(modifier = Modifier.height(2.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onClick() },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 6.dp)
            ) {
                Text(buttonText, color = Color.White)
            }
        }
    }
}