package com.example.jetpackComposeTemplate.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackComposeTemplate.app.constants.Constants.NavDestinationScreens.LOGIN_SCREEN
import com.example.jetpackComposeTemplate.app.states.AuthState.UnAuthenticated
import com.example.jetpackComposeTemplate.presentation.main.UserViewModel
import com.example.jetpackComposeTemplate.presentation.navHost.NavGraphUtil.getParentEntry
import com.example.jetpackComposeTemplate.provider.resource.ResourceProvider

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    resourceProvider: ResourceProvider,
    navController: NavController,
) {
    val userViewModel = hiltViewModel<UserViewModel>(getParentEntry(navController))
    val authState = userViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is UnAuthenticated ->{
                navController.navigate(LOGIN_SCREEN)
            }
            else -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Home Page",
            fontSize = 32.sp
        )
        TextButton(onClick = { userViewModel.logout() }) {
            Text(text = "Logout")
        }
    }

}