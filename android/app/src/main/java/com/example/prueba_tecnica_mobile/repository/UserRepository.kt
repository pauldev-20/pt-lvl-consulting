package com.example.prueba_tecnica_mobile.repository

import android.util.Log
import com.example.prueba_tecnica_mobile.data.ServiceApi
import com.example.prueba_tecnica_mobile.models.LoginCredentials
import com.example.prueba_tecnica_mobile.models.UserDao
import com.example.prueba_tecnica_mobile.models.UserProfile
import com.example.prueba_tecnica_mobile.utils.ApiResponse
import javax.inject.Inject

interface UserRepository {
    suspend fun userLogin(credentials: LoginCredentials): UserProfile?
    suspend fun userResetPassword(email: String): UserProfile?
    suspend fun userUpdate(user: UserProfile): UserProfile?
    suspend fun userLogout(user: UserProfile): Unit
    suspend fun getUserProfile(): UserProfile?
}

class UserRepositoryImp @Inject constructor(
    private val apiSource: ServiceApi,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun userLogin(credentials: LoginCredentials): UserProfile? {
        try {
            val response = apiSource.userLoginRequest(credentials)
            return response.data?.also {
                if (credentials.remember) {
                    userDao.saveUserProfile(response.data)
                }
            }
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun userResetPassword(email: String): UserProfile? {
        try {
            val response = apiSource.userResetPasswordRequest(email)
            Log.d("UserRepositoryImp", "userResetPassword: $response")
            return response.data?.also {
                userDao.updateUserProfile(response.data)
            }
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun userUpdate(user: UserProfile): UserProfile? {
        try {
            val response = apiSource.userUpdateRequest(user)
            return response.data?.also {
                userDao.updateUserProfile(response.data)
            }
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun userLogout(user: UserProfile) {
        userDao.deleteUserProfile(user)
    }

    override suspend fun getUserProfile(): UserProfile? {
        return userDao.getUserProfile()
    }

}