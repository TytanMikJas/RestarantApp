package com.example.androidapp.Utils

import android.util.Log
import com.example.androidapp.viewmodels.customer.CustomerViewModel

fun lerp(from: Float, to: Float, factor: Float): Float {
    return from.plus(to.minus(from)).times(factor)
}

fun routeToTitle(route: String, viewModel: CustomerViewModel, id: Long? = null): String {
    val firstArgIndex = route.indexOf('/')
    val parsedRoute = if (firstArgIndex > 0) route.substring(0 ..< firstArgIndex) else route
    return when {
        parsedRoute == Nav.Menu.route -> "Menu"
        parsedRoute == Nav.Waiter.route -> "Kelner"
        parsedRoute == Nav.LandingPage.route -> "O nas"
        parsedRoute == (Nav.MenuItem.route) && id != null ->  (viewModel.menu.value.find { it.id == id })?.name ?: "Pod Polakiem"
        else -> "Pod Polakiem"
    }
}