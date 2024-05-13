package com.example.androidapp.initPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.CustomerViewModel
import com.example.androidapp.R
import com.example.androidapp.Utils.Nav
import com.example.androidapp.WaiterViewModel
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun Home(navController: NavController) {
    var text by remember { mutableStateOf("") }
    val customerViewModel = CustomerViewModel.current
    val waiterViewModel = WaiterViewModel.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.restaurant_banner),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            text = "Połącz się z aplikacją jako Klient lub Kelner",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    customerViewModel.tableId.value = text.toInt()
                    customerViewModel.connectToSocketAsClient()
                    navController.popBackStack()
                    navController.navigate(Nav.LandingPage.route) {
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Klient")
            }
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Kod klienta") },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                waiterViewModel.connectToSocketAsWaiter()
                navController.popBackStack()
                navController.navigate(Nav.Waiter.route) {
                    launchSingleTop = true
                }
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Kelner")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    AndroidAppTheme {
        Home(rememberNavController())
    }
}
