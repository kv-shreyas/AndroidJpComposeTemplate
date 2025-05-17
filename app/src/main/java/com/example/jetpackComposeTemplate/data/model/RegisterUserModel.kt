package com.example.jetpackComposeTemplate.data.model

data class User(
    val email: String,
    val username: String,
    val password: String,
    val role: String? = "user"
)

data class RegisterUser(val user: User) {
    data class User(
        val email: String,
        val username: String,
        val password: String,
        val role: String? = "user"
    )
}

// Map DTO to Domain
fun RegisterUser.User.toDomain() = User(
    email = email,
    username = username,
    password = password,
    role = role
)

// Wrap Domain User in RegisterUser DTO
fun User.toRegisterUserDto() = RegisterUser(
    user = RegisterUser.User(
        email = email,
        username = username,
        password = password,
        role = role
    )
)




/*data class SignUpResponse(
    val user: UserResponse
)*/
/*data class UserResponse(val user: User) {
    data class User(
        val email: String,
        val token: String = "",
        val username: String,
        val role: String
    )
}*/



