package com.example.androidapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.androidapp.Client.LandingPage.LandingPage
import com.example.androidapp.Client.Menu.Menu
import com.example.androidapp.Client.MenuItem.MenuItem
import com.example.androidapp.Utils.Nav
import com.example.androidapp.Utils.SatisfyText
import com.example.androidapp.Utils.routeToTitle
import com.example.androidapp.Waiter.WaiterHome
import com.example.androidapp.api.dto.Category
import com.example.androidapp.api.dto.CreateOrderDto
import com.example.androidapp.api.dto.CreateOrderItemDto
import com.example.androidapp.api.dto.IOService
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.api.dto.RetrofitInstance
import com.example.androidapp.initPage.Home
import com.example.androidapp.ui.theme.AndroidAppTheme
import com.example.androidapp.viewmodels.customer.CustomerViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel

val LocalExampleViewModel = staticCompositionLocalOf<CustomerViewModel> {
    error("No CustomerViewModel provided")
}
class MainActivity : ComponentActivity() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = RetrofitInstance.api
        GlobalScope.launch {
             val response = apiService.getMenu()
            Log.d("Main", response.toString())
            }
        val ioClient = IOService(null)
        ioClient.connect()

        Thread.sleep(2000);
        ioClient.createOrder(CreateOrderDto(listOf(
            CreateOrderItemDto(1, 1),
            CreateOrderItemDto(2, 2),
            CreateOrderItemDto(3, 3)
        )))
        val customerViewModel = CustomerViewModel()

        setContent {
            CompositionLocalProvider(LocalExampleViewModel provides customerViewModel) {
                AndroidAppTheme(darkTheme = false) {
                    val navController = rememberNavController()
                    App(navController)
                }
                
            }
        }
    }
}


@Composable
fun NavigationItem(
    label: String, route: String, navController: NavController) {
    NavigationDrawerItem(
        modifier = Modifier.padding(10.dp),
        label = { },
        selected = false,
        onClick = {
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true}
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val showBackButton = remember { mutableStateOf(false)}
    val title = remember { mutableStateOf("Pod Polakiem")}
    navController.addOnDestinationChangedListener { _, destination, _ ->
        showBackButton.value = navController.previousBackStackEntry != null
        title.value = routeToTitle(destination.route ?: "")
    }

    Scaffold(
            topBar = { CenterAlignedTopAppBar(
                title = { SatisfyText(text = title.value, fontSize = 22.sp, color = MaterialTheme.colorScheme.background)  },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    if (showBackButton.value) {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.background
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        AppNavigation(Modifier.padding(top = paddingValues.calculateTopPadding()), navController)
    }

}


@Composable
fun AppNavigation(modifier: Modifier, navController: NavHostController) {
    val context = LocalContext.current
    val viewModel = LocalExampleViewModel.current
    Surface(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        color = MaterialTheme.colorScheme.background
        ) {
        NavHost(navController = navController, startDestination = "Home") {
            composable("Home") {
                Home(navController)
            }
            navigation(
                startDestination = Nav.LandingPage.route,
                route = Nav.Client.route
            ) {
                composable(Nav.LandingPage.route) {
                    LandingPage(navController)
                }
                composable(Nav.Menu.route) {
                    Menu(navController)
                }
                composable(Nav.MenuItem.route + "/{id}") { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")
                    Log.d("Main", id.toString())
                    val idLong = id?.toLong()
                    val menuItem: MenuDto = viewModel.menu.value.find { it.id == idLong } !!
                    MenuItem(menuItem = menuItem)
                }
            }
            navigation(
                startDestination = "WaiterHome",
                route = Nav.Waiter.route
            ) {
                composable("WaiterHome") {
                    WaiterHome(navController)
                }
            }
        }
    }
}



@Preview
@Composable
fun AppPreview() {
    AndroidAppTheme(darkTheme = false) {
        val navController = rememberNavController()
        App(navController)
    }
}
