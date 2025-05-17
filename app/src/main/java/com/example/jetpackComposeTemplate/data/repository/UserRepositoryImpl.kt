package com.example.jetpackComposeTemplate.data.repository

import android.util.Log
import com.example.jetpackComposeTemplate.data.model.LoginUser
import com.example.jetpackComposeTemplate.data.model.RegisterUser
import com.example.jetpackComposeTemplate.data.model.User
import com.example.jetpackComposeTemplate.data.remote.service.ApiService
import com.example.jetpackComposeTemplate.data.remote.service.LoginResponse
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: ApiService
) : UserRepository {
    private val TAG = "UserRepositoryImpl"


    override suspend fun registerUser(registerUser: RegisterUser): RegisterUser {
        Log.i(TAG, "registerUser: ${registerUser.user.username}")
        val response = userService.registerUser(registerUser)
        if (response.isSuccessful) {
            Log.i(TAG, "registerUser: success")
            return response.body() ?: throw Exception("Empty response")
        } else {
            Log.i(TAG, "registerUser: Failed ${response.code()} - ${response.message()}")
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

    override suspend fun loginUser(loginUser: LoginUser): LoginResponse {
        val response = userService.loginUser(loginUser)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response")
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

    override suspend fun getUsers(): List<User> {
        val response = userService.getUsers()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }
}
