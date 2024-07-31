package com.example.prueba_tecnica_mobile.views

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.prueba_tecnica_mobile.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba_tecnica_mobile.models.LoginCredentials
import com.example.prueba_tecnica_mobile.models.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){

    var userSession: UserProfile? by mutableStateOf(null)
    var rememberMe: Boolean by mutableStateOf(false)

    fun getNameComplete(): String {
        if (userSession == null) {
            return ""
        }
        return userSession?.names!!.split(" ")[0] + " " + userSession?.lastNames!!.split(" ")[0]
    }

    fun getCargo(): String {
        if (userSession == null) {
            return ""
        }
        return userSession?.post!! + userSession?.nameCompany!!
    }

    fun loginUser(credentials: LoginCredentials) {
            viewModelScope.launch(Dispatchers.IO) {
                rememberMe = credentials.remember
                userRepository.userLogin(credentials).let {
                    userSession = it
                }
            }
    }

    fun updateUser(user: UserProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.userUpdate(user).let {
                userSession = it
            }
        }
    }

    fun resetCredentials(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.userResetPassword(email).let {
                userSession = it
            }
        }
    }

    fun logoutUser() {
        viewModelScope.launch(Dispatchers.IO) {
            if (rememberMe && userSession != null) {
                userRepository.userLogout(userSession!!)
            }
            userSession = null
        }
    }

    fun checkUserSession() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUserProfile().let {
                userSession = it
                rememberMe = true
            }
        }
    }

}