package com.example.androidapp.Client.Waiting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.Client.Basket.BasketItems
import com.example.androidapp.CustomerViewModel
import com.example.androidapp.Utils.Nav
import com.example.androidapp.api.dto.Status
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun Waiting(navController: NavController){
    val viewModel = CustomerViewModel.current
    val status = viewModel.order.value?.status
    val calculatedTime = 600000L
    val orderStatus = viewModel.order.value


    Column (modifier = Modifier
        .fillMaxHeight()
        .padding(16.dp)){
        when (status) {
            Status.PLACED -> {
                OrderPlaced()
            }
            Status.IN_PROGRESS -> {
                OrderInProgress()
            }
            Status.DELIVERED -> {
                OrderDelivered()
            }
            else -> {}
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Divider()

        Spacer(modifier = Modifier.padding(16.dp))

        when (status) {
            Status.PLACED -> {
                BasketItems(items = viewModel.order.value!!.items)
            }
            Status.IN_PROGRESS -> {
                Spacer(modifier = Modifier.padding(32.dp))
                CountDown(totalTime = calculatedTime,
                    handleColor = Color.Red,
                    inactiveBarColor = Color.DarkGray,
                    activeBarColor = Color(0xFFCC0000),
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally))
            }
            Status.DELIVERED -> {
                Column(
                       modifier = Modifier
                            .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {

                    BasketItems( items = viewModel.order.value!!.items)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                    ){

                        Button(onClick = { navController.navigate(Nav.LandingPage.route){
                        } }) {
                            Text(text = "Powrót", style = MaterialTheme.typography.bodyLarge)

                        }
                    }
                }
            }
            else -> {}
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidAppTheme {
        Waiting(navController = rememberNavController())
    }
}