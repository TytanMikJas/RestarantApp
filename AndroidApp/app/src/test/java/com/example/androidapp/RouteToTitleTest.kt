package com.example.androidapp

import androidx.compose.runtime.mutableStateOf
import com.example.androidapp.Utils.Nav
import com.example.androidapp.Utils.routeToTitle
import com.example.androidapp.api.dto.Category
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.viewmodels.customer.CustomerViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

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
    fun route_menu_item_to_correct_title() {
        MockitoAnnotations.openMocks(this)

        val viewModel = mock(CustomerViewModel()::class.java)
        val menuDto = MenuDto(
            id = 1L,
            name = "Schabowy z ziemniaczkami",
            description = "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
            alergens = listOf("gluten"),
            price = 21.20,
            category = Category.BREAKFAST,
            images =  listOf("https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v"),
            video = null
        )
        val menuDto2 = MenuDto(
            id = 2L,
            name = "Barszcz z uszkami",
            description = "",
            alergens = listOf("gluten"),
            price = 21.20,
            category = Category.BREAKFAST,
            images =  listOf("https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v"),
            video = null
        )
        val menuList = listOf(menuDto, menuDto2)
        `when`(viewModel.menu).thenReturn(mutableStateOf(menuList))

        val title = routeToTitle(Nav.MenuItem.route, viewModel, 1L)
        val title2 = routeToTitle(Nav.MenuItem.route, viewModel, 2L)

        assertEquals("Schabowy z ziemniaczkami", title)
        assertEquals("Barszcz z uszkami", title2)

    }

    @Test
    fun route_menu_item_incorrect_id_to_correct_title() {
        MockitoAnnotations.openMocks(this)

        val viewModel = mock(CustomerViewModel()::class.java)
        val menuDto = MenuDto(
            id = 1L,
            name = "Schabowy z ziemniaczkami",
            description = "Klasa sama w sobie, pyszne danie łączące tradycję z nowoczesnością",
            alergens = listOf("gluten"),
            price = 21.20,
            category = Category.BREAKFAST,
            images =  listOf("https://www.winiary.pl/sites/default/files/styles/recipe/public/recipe/2020-07/schabowy_z_ziemniaczkami_1.jpg?itok=3Z6Z9Q8v"),
            video = null
        )
        val menuList = listOf(menuDto)
        `when`(viewModel.menu).thenReturn(mutableStateOf(menuList))

        val title = routeToTitle(Nav.MenuItem.route, viewModel, 2L)

        assertEquals("Pod Polakiem", title)

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
        val title4 = routeToTitle("test/var", CustomerViewModel())
        assertEquals("Menu", title1)
        assertEquals("Kelner", title2)
        assertEquals("O nas", title3)
        assertEquals("Pod Polakiem", title4)
    }
}