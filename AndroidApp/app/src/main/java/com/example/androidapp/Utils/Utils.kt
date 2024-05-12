package com.example.androidapp.Utils

import android.util.Log

fun lerp(from: Float, to: Float, factor: Float): Float {
    return from.plus(to.minus(from)).times(factor)
}

fun routeToTitle(route: String): String {
    val id = extractIdFromRoute(route)
    val firstArgIndex = route.indexOf('{')
    val parsedRoute = if (firstArgIndex > 0) route.substring(0 ..< firstArgIndex) else route
    return when {
        parsedRoute == Nav.Menu.route -> "Menu"
        parsedRoute == Nav.Waiter.route -> "Kelner"
        parsedRoute == Nav.LandingPage.route -> "O nas"
        parsedRoute == (Nav.MenuItem.route) && id != null -> "Schabowy z ziemniaczkami" // TODO - znaleźć tytuł dania po id
        else -> "Pod Polakiem"
    }
}

fun extractIdFromRoute(route: String): Long? {
    val regex = "\\{(\\d+)\\}".toRegex()
    val matchResult = regex.find(route)
    return matchResult?.groupValues?.get(1)?.toLongOrNull()
}