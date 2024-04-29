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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.ui.theme.AndroidAppTheme

data class Order(val id: String, val table: String, val items: List<Pair<String, String>>)
val pendingOrders = listOf(
    Order("#2341", "Stolik 15", listOf("Schabowy z ziemniakami" to "53,72zł", "Kompot śliwkowy" to "9,99zł")),
    Order("#2342", "Stolik 8", listOf("Pierogi ruskie" to "25,00zł", "Barszcz czerwony" to "12,00zł")),
    Order("#2343", "Stolik 12", listOf("Zurek w chlebie" to "17,00zł", "Placki ziemniaczane" to "20,00zł")),
    Order("#2344", "Stolik 3", listOf("Golabki" to "30,00zł", "Mizeria" to "7,00zł")),
    Order("#2345", "Stolik 20", listOf("Kaczka z jabłkami" to "45,00zł", "Sok pomarańczowy" to "6,00zł"))
)

val preparingOrders = listOf(
    Order("#2210", "Stolik 5", listOf("Herbata żurawinowa 250ml" to "5,25zł", "Kapuściak 500ml" to "9,99zł")),
    Order("#2211", "Stolik 7", listOf("Bigos" to "18,00zł", "Kompot malinowy" to "8,50zł")),
    Order("#2212", "Stolik 2", listOf("Stek wieprzowy" to "40,00zł", "Piwo ciemne" to "10,00zł")),
    Order("#2213", "Stolik 4", listOf("Kotlet mielony" to "22,00zł", "Woda gazowana" to "5,00zł")),
    Order("#2214", "Stolik 6", listOf("Naleśniki z serem" to "19,00zł", "Herbata miętowa" to "6,00zł"))
)

val deliveredOrders = listOf(
    Order("#2351", "Stolik 13", listOf("Łazanki" to "8,10zł", "Kompot śliwkowy" to "9,99zł")),
    Order("#2352", "Stolik 20", listOf("Filet z dorsza" to "35,00zł", "Zielona herbata" to "7,50zł")),
    Order("#2353", "Stolik 1", listOf("Pizza Margherita" to "24,00zł", "Cola" to "6,00zł")),
    Order("#2354", "Stolik 11", listOf("Spaghetti Bolognese" to "27,00zł", "Sok jabłkowy" to "6,50zł")),
    Order("#2355", "Stolik 9", listOf("Sushi set" to "50,00zł", "Sake" to "20,00zł"))
)


@Composable
fun WaiterHome(navController: NavController) {
    val context = LocalContext.current
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            ColumnTitle(text = "Zamówienia oczekujące")
            LazyColumn {
                items(pendingOrders) { order ->
                    OrderCard(order, Color.Cyan.copy(alpha = 0.3f))
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
                    OrderCard(order, Color.Yellow.copy(alpha = 0.5f))
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
                    OrderCard(order, Color.Green.copy(alpha = 0.3f))
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