package com.example.jetpackComposeTemplate.data.remote.service

import com.example.jetpackComposeTemplate.app.constants.Constants
import com.example.jetpackComposeTemplate.data.model.LoginUser
import com.example.jetpackComposeTemplate.data.model.RegisterUser
import com.example.jetpackComposeTemplate.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
/*    @GET("/users")
    suspend fun getUsers(): List<User>*/

    @POST(Constants.ApiEndpoints.REGISTER_ENDPOINT)
    suspend fun registerUser(@Body registerUser: RegisterUser): Response<RegisterUser>

    @POST(Constants.ApiEndpoints.LOGIN_ENDPOINT)
    suspend fun loginUser(@Body credentials: LoginUser): Response<LoginResponse>

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}

/*
data class LoginRequest(
    val email: String,
    val password: String
)
*/

data class LoginResponse(
    val token: String,
    val user: User
)


