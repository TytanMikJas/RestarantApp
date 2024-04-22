package com.example.androidapp.Utils

sealed class Nav(val route: String) {
    data object Home : Nav("home")
    data object Client : Nav("client")
    data object ClientSetup : Nav("clientSetup")
    data object ClientHome : Nav("clientHome")
    data object Waiter : Nav("waiter")
}