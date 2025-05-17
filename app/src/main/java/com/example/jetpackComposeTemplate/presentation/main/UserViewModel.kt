package com.example.jetpackComposeTemplate.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackComposeTemplate.app.base.BaseViewModel
import com.example.jetpackComposeTemplate.app.states.AuthState
import com.example.jetpackComposeTemplate.data.model.LoginUser
import com.example.jetpackComposeTemplate.data.model.RegisterUser
import com.example.jetpackComposeTemplate.data.model.User
import com.example.jetpackComposeTemplate.data.remote.service.LoginResponse
import com.example.jetpackComposeTemplate.domain.useCases.user.UserUseCases
import com.example.jetpackComposeTemplate.provider.resource.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {
    private val TAG = "UserViewModel"

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> get() = _loginResponse

    private val _authState = MutableLiveData<AuthState>(AuthState.Unknown)
    val authState: LiveData<AuthState> get() = _authState


    fun registerUser(registerUser: RegisterUser) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val token = userUseCases.registerUser(registerUser)
                Log.i(TAG, "registerUser: token $token")
                _authState.value = AuthState.Authenticated // Emit success state
            } catch (e: Exception) {
                _authState.value =
                    AuthState.Error(e.message ?: "something went wrong")

            }
        }
    }

    fun loginUser(loginUser: LoginUser) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val result = userUseCases.loginUser(loginUser)
                Log.i(TAG, "loginUser: token: ${result.token}")

                _authState.value = AuthState.Authenticated // Emit success state
                _loginResponse.postValue(result)

            } catch (e: Exception) {
                _authState.value =
                    AuthState.Error(e.message ?: "something went wrong")
            }
        }
    }

    fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = userUseCases.getUsersListUseCase()
                _users.postValue(result)
            } catch (e: Exception) {
                _authState.value =
                    AuthState.Error(e.message ?: "something went wrong")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                _authState.value = AuthState.UnAuthenticated
//                clearAuthState()
            } catch (e: Exception) {
                _authState.value =
                    AuthState.Error(e.message ?: "something went wrong")
            }
        }
    }

    fun clearAuthState() {
        _authState.value = AuthState.Unknown
    }
}

