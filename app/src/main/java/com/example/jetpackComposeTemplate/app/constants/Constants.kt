package com.example.jetpackComposeTemplate.app.constants

class Constants {
    object NavDestinationScreens {
        const val HOME_SCREEN = "home_screen"
        const val LOGIN_SCREEN = "login_screen"
        const val REGISTER_SCREEN = "register_screen"
        const val SETTINGS_SCREEN = "settings_screen"
        const val SCANNER_SCREEN = "scanner_screen"
    }

    object ApiEndpoints {
        const val BASE_URL = "http://172.11.0.6:8080/nabhmitra/"
        const val REGISTER_ENDPOINT = "users"
        const val LOGIN_ENDPOINT = "users/login"
    }


}