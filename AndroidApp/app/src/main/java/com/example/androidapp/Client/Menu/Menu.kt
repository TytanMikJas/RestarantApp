package com.example.androidapp.Client.Menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun Menu(navController: NavController){



}

@Preview(showBackground = true)
@Composable
fun PreviewMenu(){
    AndroidAppTheme {
        Menu(rememberNavController())
    }
}