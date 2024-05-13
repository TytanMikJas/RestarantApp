package com.example.androidapp.Client.Basket

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import com.example.androidapp.CustomerViewModel
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun Basket(navController: NavController) {
    val viewModel = CustomerViewModel.current


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

        BasketItems(items = viewModel.cart.value)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { viewModel.createOrder() },
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