package com.example.prueba_tecnica_mobile.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prueba_tecnica_mobile.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.prueba_tecnica_mobile.components.TextFieldComponent
import com.example.prueba_tecnica_mobile.dialogs.ConfirmDialog
import com.example.prueba_tecnica_mobile.models.LoginCredentials
import com.example.prueba_tecnica_mobile.navigation.AppScreens
import com.example.prueba_tecnica_mobile.views.UserViewModel
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun LoginScreen(navController: NavController, viewModel: UserViewModel) {
    var email by remember { mutableStateOf("miguelliberato@gmail.com") }
    var password by remember { mutableStateOf("12345678") }
    var rememberMe by remember { mutableStateOf(false) }
    var showResetPasswordDialog by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.userSession) {
        if (viewModel.userSession != null) {
            navController.popBackStack()
            navController.navigate(AppScreens.HomeScreen.route)
        }
    }
    LaunchedEffect(Unit) {
        viewModel.checkUserSession()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginHeader()
        Spacer(modifier = Modifier.height(24.dp))
        LoginForm(
            email = email,
            onEmailChange = { email = it.trim() },
            password = password,
            onPasswordChange = { password = it },
            onLoginClick = { viewModel.loginUser(LoginCredentials( email, password, rememberMe))},
            onForgotPasswordClick = { showResetPasswordDialog = true},
            rememberMe = rememberMe,
            onRememberMeChange = { rememberMe = it }
        )
    }
    if (showResetPasswordDialog) {
        ConfirmDialog(
            onDismiss = { showResetPasswordDialog = false },
            onConfirm = { viewModel.resetCredentials(email.trim()) },
        )
    }
}

@Composable
fun LoginHeader() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(140.dp)
                .align(Alignment.Start)
        )
        Text(
            text = "¡Te damos la bienvenida!",
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "a JIRA Atlassian",
            style = MaterialTheme.typography.h4.copy(color = Color.Blue),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Hola, inicia sesión para continuar",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    rememberMe: Boolean,
    onRememberMeChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TextFieldComponent(
            label = "Correo electrónico",
            value = email,
            onValueChange = onEmailChange,
            placeHolder = "Ingrese su correo electrónico"
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldComponent(
            label = "Contraseña",
            value = password,
            onValueChange = onPasswordChange,
            isPassword = true,
            placeHolder = "Ingrese su contraseña"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = onRememberMeChange
            )
            Text(text = "Recordarme")
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = onForgotPasswordClick) {
                Text(text = "Olvidé mi contraseña", color = Color.Blue)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick =  {
                onLoginClick() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 20.sp,
                modifier = Modifier.padding(5.dp,10.dp).clip(RoundedCornerShape(20.dp))
            )
        }
    }
}