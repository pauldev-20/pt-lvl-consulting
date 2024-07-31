package com.example.prueba_tecnica_mobile.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba_tecnica_mobile.screens.*
import com.example.prueba_tecnica_mobile.ui.SplashScreen
import com.example.prueba_tecnica_mobile.views.ProjectViewModel
import com.example.prueba_tecnica_mobile.views.UserViewModel

@RequiresApi(34)
@Composable
fun AppNavigation(
    userViewModel: UserViewModel = hiltViewModel(),
    projectViewModel: ProjectViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route ) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.LoginScreen.route) {
            LoginScreen(navController, userViewModel)
        }
        composable(AppScreens.HomeScreen.route) {
            HomeScreen(navController, userViewModel, projectViewModel)
        }
        composable(AppScreens.CreateProjectScreen.route) {
            CreateProjectScreen(navController, userViewModel, projectViewModel)
        }
        composable(AppScreens.ProfileScreen.route) {
            ProfileScreen(navController, userViewModel)
        }
        composable(AppScreens.CameraScreen.route) {
            CameraScreen(navController)
        }
    }
}