package com.example.prueba_tecnica_mobile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prueba_tecnica_mobile.R
import com.example.prueba_tecnica_mobile.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(key1 = true){
        delay(2000)
        navController.popBackStack()
        navController.navigate(AppScreens.LoginScreen.route)
    }
    Splash()
}

@Composable
fun Splash(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Jira",
            modifier =  Modifier.size(200.dp, 200.dp)
        )
    }
}