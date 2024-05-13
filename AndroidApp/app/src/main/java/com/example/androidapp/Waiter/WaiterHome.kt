package com.example.androidapp.Waiter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.WaiterViewModel
import com.example.androidapp.api.dto.OrderDto
import com.example.androidapp.api.dto.Status
import com.example.androidapp.ui.theme.AndroidAppTheme




@Composable
fun WaiterHome(navController: NavController) {
    val context = LocalContext.current
    val waiterViewModel = WaiterViewModel.current
    val pendingOrders: List<OrderDto> = waiterViewModel.orders.value.filter { it.status == Status.PLACED }
    val preparingOrders: List<OrderDto> = waiterViewModel.orders.value.filter { it.status == Status.IN_PROGRESS }
    val deliveredOrders: List<OrderDto> = waiterViewModel.orders.value.filter { it.status == Status.DELIVERED }
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            ColumnTitle(text = "Zamówienia oczekujące")
            LazyColumn {
                items(pendingOrders) { order ->
                    OrderCard(order, Color.Cyan.copy(alpha = 0.3f), "Przyjmij")
                }
            }
        }
        Divider(color = Color.Gray.copy(alpha = 0.6f), modifier = Modifier
            .align(Alignment.CenterVertically)
            .fillMaxHeight(0.75f)
            .width(2.dp))
        Column(modifier = Modifier.weight(1f)) {
            ColumnTitle(text = "W trakcie przygotowania")
            LazyColumn {
                items(preparingOrders) { order ->
                    OrderCard(order, Color.Yellow.copy(alpha = 0.5f), "Dostarczono")
                }
            }
        }
        Divider(color = Color.Gray.copy(alpha = 0.6f), modifier = Modifier
            .align(Alignment.CenterVertically)
            .fillMaxHeight(0.75f)
            .width(2.dp))
        Column(modifier = Modifier.weight(1f)) {
            ColumnTitle(text = "Zamówienia dostarczone")
            LazyColumn {
                items(deliveredOrders) { order ->
                    OrderCard(order, Color.Green.copy(alpha = 0.3f), "Archiwizuj")
                }
            }
        }
    }
}


@Preview(showBackground = true,
    device = "spec:width=1100dp,height=693dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape")
@Composable
fun PreviewWaiterHome(){
    AndroidAppTheme {
        WaiterHome(rememberNavController())
    }
}