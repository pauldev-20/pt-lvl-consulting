package com.example.prueba_tecnica_mobile.data

import com.example.prueba_tecnica_mobile.models.LoginCredentials
import com.example.prueba_tecnica_mobile.models.Project
import com.example.prueba_tecnica_mobile.models.ProjectCreate
import com.example.prueba_tecnica_mobile.models.UserProfile
import com.example.prueba_tecnica_mobile.utils.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @POST("auth/login")
    suspend fun userLoginRequest(@Body credentials: LoginCredentials): ApiResponse<UserProfile>

    @POST("auth/reset-credentials")
    suspend fun userResetPasswordRequest(@Body email: String): ApiResponse<UserProfile>

    @PUT("users")
    suspend fun userUpdateRequest(@Body user: UserProfile): ApiResponse<UserProfile>

    @GET("users/{userId}/projects")
    suspend fun getProjectsRequest(@Path("userId") userId: Int): ApiResponse<List<Project>>

    @POST("projects")
    suspend fun createProjectRequest(@Body project: ProjectCreate): ApiResponse<Project>

    @GET("projects")
    suspend fun getProjectsAdvanceRequest(
        @Query("user_id") userId: Int,
        @Query("code") code: String? = "all",
        @Query("name") name: String? = "all",
        @Query("state") state: String? = "all",
        @Query("date_start") dateStart: String? = "all",
        @Query("date_end") dateEnd: String? = "all"
    ): ApiResponse<List<Project>>
}