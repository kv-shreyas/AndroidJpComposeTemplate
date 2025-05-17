package com.example.jetpackComposeTemplate.presentation.navHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.jetpackComposeTemplate.app.constants.Constants
import com.example.jetpackComposeTemplate.presentation.home.HomePage
import com.example.jetpackComposeTemplate.provider.resource.ResourceProvider
import com.example.shreychat.screen.LoginPage
import com.example.shreychat.screen.RegisterPage

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, resourceProvider: ResourceProvider) {
    val navController = rememberNavController()
    val currentUser = null
    val start = Constants.NavDestinationScreens.LOGIN_SCREEN; // if(currentUser != null) "home" else "login"

    NavHost(navController = navController, startDestination = NavGraphUtil.NAV_GRAPH_ROUTE, builder = {

        navigation(
            startDestination = start,
            route = NavGraphUtil.NAV_GRAPH_ROUTE
        ) {
            composable(Constants.NavDestinationScreens.LOGIN_SCREEN) {
                LoginPage(modifier = modifier, resourceProvider, navController)
            }
            composable(Constants.NavDestinationScreens.HOME_SCREEN) {
                HomePage(modifier = modifier, resourceProvider, navController)
            }
            composable(Constants.NavDestinationScreens.REGISTER_SCREEN) {
                RegisterPage(modifier = modifier, resourceProvider, navController)
            }
        }
    })


}