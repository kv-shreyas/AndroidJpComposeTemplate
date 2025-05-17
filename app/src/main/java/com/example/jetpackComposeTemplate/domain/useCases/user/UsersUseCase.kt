package com.example.jetpackComposeTemplate.domain.useCases.user

import com.example.jetpackComposeTemplate.data.remote.service.LoginResponse
import com.example.jetpackComposeTemplate.data.model.RegisterUser
import com.example.jetpackComposeTemplate.data.model.User
import com.example.jetpackComposeTemplate.data.model.LoginUser
import com.example.jetpackComposeTemplate.data.repository.UserRepository
import javax.inject.Inject

class UserUseCases @Inject constructor(
    val registerUser: RegisterUserUseCase,
    val loginUser: LoginUserUseCase,
    val getUsersListUseCase: GetUsersListUseCase
)

class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(registerUser: RegisterUser): RegisterUser {
        return userRepository.registerUser(registerUser)
    }
}

class LoginUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(loginUser: LoginUser): LoginResponse {
        return userRepository.loginUser(loginUser)
    }
}

class GetUsersListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): List<User> {
        return userRepository.getUsers()
    }
}



