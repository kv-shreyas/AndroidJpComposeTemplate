package com.example.jetpackComposeTemplate.data.repository

import com.example.jetpackComposeTemplate.data.remote.service.LoginResponse
import com.example.jetpackComposeTemplate.data.model.RegisterUser
import com.example.jetpackComposeTemplate.data.model.User
import com.example.jetpackComposeTemplate.data.model.LoginUser

interface UserRepository {
    suspend fun registerUser(registerUser: RegisterUser): RegisterUser
    suspend fun loginUser(loginUser: LoginUser): LoginResponse
    suspend fun getUsers(): List<User>
}
