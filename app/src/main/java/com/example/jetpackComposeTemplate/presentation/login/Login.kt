package com.example.shreychat.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackComposeTemplate.R
import com.example.jetpackComposeTemplate.app.constants.Constants
import com.example.jetpackComposeTemplate.app.states.AuthState
import com.example.jetpackComposeTemplate.data.model.UserLoginCredentials
import com.example.jetpackComposeTemplate.data.model.toLoginUserDto
import com.example.jetpackComposeTemplate.presentation.main.UserViewModel
import com.example.jetpackComposeTemplate.presentation.navHost.NavGraphUtil
import com.example.jetpackComposeTemplate.presentation.navHost.NavGraphUtil.getParentEntry
import com.example.jetpackComposeTemplate.provider.resource.ResourceProvider


@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    resourceProvider: ResourceProvider,
    navController: NavController
) {
    val loginVm = hiltViewModel<UserViewModel>(getParentEntry(navController))

    val authState = loginVm.authState.observeAsState()
    val context = LocalContext.current


    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }


    LaunchedEffect(authState.value) {
        Log.i("kvs", "LoginPage: " + authState.value.toString())
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate(Constants.NavDestinationScreens.HOME_SCREEN) {
                popUpTo(Constants.NavDestinationScreens.LOGIN_SCREEN) {
                    inclusive = true
                }// Clears the login page from the back stack
                launchSingleTop = true
            }

            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).string,
                Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }

    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = resourceProvider.getString(R.string.email)) })


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = resourceProvider.getString(R.string.password)) })

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val user = UserLoginCredentials(email = email, password = password)
                loginVm.loginUser(user.toLoginUserDto())
            },
            enabled = authState.value !is AuthState.Loading
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = {
            NavGraphUtil.gotoScreen(
                navController,
                Constants.NavDestinationScreens.REGISTER_SCREEN,
                true
            )        }
        ) {
            Text(text = "Create New Account.")
        }


    }

}