package com.example.prueba_tecnica_mobile.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object HomeScreen: AppScreens("home_screen")
    object CreateProjectScreen: AppScreens("create_project_screen")
    object ProfileScreen: AppScreens("profile_screen")
    object CameraScreen: AppScreens("camera_screen")
}