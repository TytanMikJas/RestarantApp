package com.example.androidapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.api.dto.RetrofitInstance
import com.example.androidapp.initPage.Home
import com.example.androidapp.ui.theme.AndroidAppTheme
import com.example.androidapp.viewmodels.customer.CustomerViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.androidapp.Client.Basket.Basket
import com.example.androidapp.Client.Waiting.Waiting
import com.example.androidapp.api.dto.Status
import com.example.androidapp.viewmodels.waiter.WaiterViewModel

val CustomerViewModel = staticCompositionLocalOf<CustomerViewModel> {
    error("No CustomerViewModel provided")
}

val WaiterViewModel = staticCompositionLocalOf<WaiterViewModel> {
    error("No WaiterViewModel provided")
}

class MainActivity : ComponentActivity() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customerViewModel = CustomerViewModel()
        val waiterViewModel = WaiterViewModel()

        setContent {
            CompositionLocalProvider(CustomerViewModel provides customerViewModel) {
                CompositionLocalProvider(WaiterViewModel provides waiterViewModel) {
                    AndroidAppTheme(darkTheme = false) {
                        val navController = rememberNavController()
                        App(navController)
                    }
                }
                
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val viewModel = CustomerViewModel.current
    val showBackButton = remember { mutableStateOf(false)}
    val title = remember { mutableStateOf("Pod Polakiem")}
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    navController.addOnDestinationChangedListener { _, destination, arguments ->
        showBackButton.value = navController.previousBackStackEntry != null
        val id: Long? = arguments?.getString("id")?.toLongOrNull()
        title.value = routeToTitle(destination.route ?: "", viewModel, id)
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
                            onBackPressedDispatcher?.onBackPressed()
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
    val viewModel = CustomerViewModel.current
    LaunchedEffect(viewModel.order.value?.status) {
        if (viewModel.tableId.value == null) {
            return@LaunchedEffect
        }
        when (viewModel.order.value?.status) {
            Status.DELIVERED -> {
                Log.d("Navigation", "Status: DELIVERED")
                // Navigate to Waiting and clear the back stack
                navController.navigate(Nav.Waiting.route) {
                    popUpTo(0) { inclusive = true }
                }
            }

            else -> {
                Log.d("Navigation", "Status: ${viewModel.order.value?.status}")
                // Navigate to Waiting and clear the back stack
                navController.navigate(Nav.Waiting.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }

    BackHandler(enabled = viewModel.order.value?.status == Status.DELIVERED) {
        navController.navigate(Nav.LandingPage.route) {
            launchSingleTop = true
        }
    }







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
                startDestination = Nav.LandingPage.route, route = Nav.Client.route
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
                    val menuItem: MenuDto = viewModel.menu.value.find { it.id == idLong }!!
                    MenuItem(menuItem = menuItem)
                }
                composable(Nav.Basket.route) {
                    Basket(navController)
                }
                composable(Nav.Waiting.route) {
                    Waiting(navController)
                }
            }
            navigation(
                startDestination = "WaiterHome", route = Nav.Waiter.route
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
