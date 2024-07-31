package com.example.prueba_tecnica_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prueba_tecnica_mobile.models.LoginCredentials
import com.example.prueba_tecnica_mobile.navigation.AppNavigation
import com.example.prueba_tecnica_mobile.screens.LoginScreen
import com.example.prueba_tecnica_mobile.ui.theme.PruebaTecnicaMobileTheme
import com.example.prueba_tecnica_mobile.views.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(34)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           /* PruebaTecnicaMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   AppNavigation()
                }
            }*/
            AppNavigation()
        }
    }
}