package com.example.jetpackComposeTemplate.data.model


data class UserLoginCredentials(
    val email: String,
    val password: String
)

data class LoginUser(val user: UserLoginCredentials) {
    data class UserLoginCredentials(
        val email: String,
        val password: String
    )
}

fun UserLoginCredentials.toLoginUserDto() = LoginUser(
    user = LoginUser.UserLoginCredentials(
        email = email,
        password = password
    )
)