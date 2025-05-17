package com.example.jetpackComposeTemplate.presentation.navHost

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import kotlin.let

object NavGraphUtil {
    const val NAV_GRAPH_ROUTE = "Main_Graph"

    /**
     * Navigate to the given [toScreen] destination.
     * @param popUpToCurrent if true, the current screen is removed from the back stack.
     * @param clearBackStack if true, clears the entire back stack before navigating.
     */
    fun gotoScreen(
        navController: NavController,
        toScreen: String,
        popUpToCurrent: Boolean = false,
        clearBackStack: Boolean = false
    ) {
        val currentScreen = navController.currentBackStackEntry?.destination?.route

        navController.navigate(toScreen) {
            when {
                clearBackStack -> {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
                popUpToCurrent -> {
                    currentScreen?.let {
                        popUpTo(it) {
                            inclusive = true
                        }
                    }
                }
            }
            launchSingleTop = true
        }
    }

    /**
     * Returns the [NavBackStackEntry] of the parent route (Main_Graph in this case).
     */
    fun getParentEntry(navController: NavController): NavBackStackEntry {
        return try {
            navController.getBackStackEntry(NAV_GRAPH_ROUTE)
        } catch (e: IllegalArgumentException) {
            navController.currentBackStackEntry
                ?: navController.getBackStackEntry(navController.graph.startDestinationRoute!!)
        }
    }

    fun NavController.safePopBackStack() {
        if (previousBackStackEntry != null) {
            popBackStack()
        }
    }

}
