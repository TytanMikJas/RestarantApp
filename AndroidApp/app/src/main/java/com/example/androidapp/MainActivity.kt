package com.example.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.androidapp.Client.LandingPage.ClientHome
import com.example.androidapp.Client.Menu.MenuItemDto
import com.example.androidapp.Client.MenuItem.MenuItem
import com.example.androidapp.Utils.Nav
import com.example.androidapp.Waiter.WaiterHome
import com.example.androidapp.initPage.Home
import com.example.androidapp.ui.theme.AndroidAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidAppTheme(darkTheme = false) {
                val navController = rememberNavController()
                App(navController)
            }
        }
    }
}


@Composable
fun NavigationItem(
    label: String, route: String, navController: NavController) {
    NavigationDrawerItem(
        modifier = Modifier.padding(10.dp),
        label = { Text(text = label) },
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

    Scaffold(
            topBar = { CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.restaurant_banner)) }
            )
        }
    ) { paddingValues ->
        AppNavigation(Modifier.padding(top = paddingValues.calculateTopPadding()), navController)
    }

}


@Composable
fun AppNavigation(modifier: Modifier, navController: NavHostController) {
    val context = LocalContext.current

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
                startDestination = Nav.ClientHome.route,
                route = Nav.Client.route
            ) {
                composable(Nav.ClientHome.route) {
                    ClientHome(navController)
                }
                composable(Nav.MenuItem.route) {
                    MenuItem(menuItem = MenuItemDto(
                        3,
                        "Kotlet schabowy",
                        "Kotlet schabowy z ziemniakami i surówką",
                        listOf("gluten"),
                        18.50f,
                        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg", "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg"),
                        "https://example.com/video.mp4",
                        "LUNCH"))
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