package com.example.jetpackComposeTemplate.app.states

sealed class AuthState {
    object Authenticated : AuthState()
    object UnAuthenticated : AuthState()
    object Loading : AuthState()
    object Unknown : AuthState()
    data class Error(val string: String) : AuthState()
}