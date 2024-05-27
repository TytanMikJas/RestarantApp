package com.example.androidapp

import com.example.androidapp.Utils.Nav
import com.example.androidapp.Utils.routeToTitle
import com.example.androidapp.viewmodels.customer.CustomerViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RouteToTitleTest {


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun route_menu_to_correct_title() {
        val title = routeToTitle(Nav.Menu.route, CustomerViewModel())

        assertEquals("Menu", title)
    }

    @Test
    fun route_waiter_to_correct_title() {
        val title = routeToTitle(Nav.Waiter.route, CustomerViewModel())

        assertEquals("Kelner", title)
    }

    @Test
    fun route_landing_page_to_correct_title() {
        val title = routeToTitle(Nav.LandingPage.route, CustomerViewModel())

        assertEquals("O nas", title)
    }

    @Test
    fun route_default_to_correct_title() {
        val title1 = routeToTitle("test1", CustomerViewModel())
        val title2 = routeToTitle("test2", CustomerViewModel())
        assertEquals("Pod Polakiem", title1)
        assertEquals("Pod Polakiem", title2)
    }

    @Test
    fun route_with_variables_to_correct_title() {
        val title1 = routeToTitle(Nav.Menu.route + "/var", CustomerViewModel())
        val title2 = routeToTitle(Nav.Waiter.route +"/var", CustomerViewModel())
        val title3 = routeToTitle(Nav.LandingPage.route + "/var", CustomerViewModel())
        assertEquals("Menu", title1)
        assertEquals("Kelner", title2)
        assertEquals("O nas", title3)
    }
}